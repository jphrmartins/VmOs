package vm;

import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.InvalidRuleInterruption;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;
import vm.interruptions.list.ProgramOutOfBoundsInterruption;
import vm.memory.CPUState;
import vm.memory.PCB;

import java.util.Optional;
import java.util.Set;

public class CPU extends Thread {
    private static final Integer REGISTER_LENGTH = 9;
    private int programCounter;             // ... composto de program counter,
    private int clockCycle;
    private int globalClock;
    private final OperationalSystem operationalSystem; //Pointer to the sysops
    private int[] registries; //Registradores
    private final Word[] memory;   //vm.CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    private final Set<InstructionRule> instructionRules;
    private PCB currentPCB;

    public CPU(OperationalSystem operationalSystem, Word[] memory, Set<InstructionRule> instructionRules) {// ref a MEMORIA e interrupt handler passada na criacao da vm.CPU
        super("CPU THREAD");
        this.memory = memory;                // usa o atributo 'm' para acessar a memoria.
        this.clockCycle = 1;
        this.globalClock = 1;
        this.registries = new int[REGISTER_LENGTH];     // aloca o espaço dos registradores
        this.instructionRules = instructionRules;
        this.operationalSystem = operationalSystem;
    }

    public int[] getRegistries() {
        return registries;
    }

    /**
     * Accessor method the get the cpu memory
     *
     * @return The memory
     */
    public Word[] getMemory() {
        return memory;
    }

    public PCB getCurrentPCB() {
        return currentPCB;
    }

    public void cleanCurrentPCB() {
        clockCycle = 1;
        currentPCB = null;
    }

    public boolean isIdle() {
        return currentPCB == null;
    }

    public void setContext(int pc) {  // no futuro esta funcao vai ter que ser
        this.programCounter = pc;                                              // limite e pc (deve ser zero nesta versao)
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void incrementPc() {
        this.programCounter++;
    }

    @Override
    public void run() {        // execucao da vm.CPU supoe que o contexto da vm.CPU, vide acima, esta devidamente setado
        // instruction register
        Word instruction;
        while (true) { // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            if (currentPCB != null) {
                SystemInterrupt interrupt;
                Optional<Integer> programCounterPosition = currentPCB.getMemoryPosition(programCounter);
                if (programCounter > memory.length) {
                    interrupt = new MemoryOutOfBoundsInterruption(programCounter, memory.length);
                } else if (programCounterPosition.isEmpty()) {
                    interrupt = new ProgramOutOfBoundsInterruption(currentPCB, programCounter);
                } else {
                    instruction = memory[programCounterPosition.get()];    // busca posicao da memoria apontada por pc, guarda em instruction
                    interrupt = new InvalidRuleInterruption(instruction.getOpc());
                    for (InstructionRule rule : instructionRules) {
                        if (rule.shouldExecute(instruction.getOpc())) {
                            interrupt = rule.executeRule(this, instruction);
                            break;
                        }
                    }
                }
                if (interrupt != null) {
                    operationalSystem.handleInterruption(interrupt);
                }
                if (clockCycle == 5 || currentPCB != null && currentPCB.isBlocked()) {
                    clockCycle = 1;
                    operationalSystem.handleProgramChange();
                }
                clockCycle++;
            } else {
                synchronized (this) {
                    try {
                        System.out.println("CURRENT STATE OF CPU SHOULD BE IDLE SINCE CURRENT_PCB IS NULL, will wait... Global clock: " + globalClock);
                        wait();
                        System.out.println("New Program input");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            globalClock++;
        }
    }

    public void saveCurrentState() {
        currentPCB.saveState(this);
    }

    public void loadStateOf(PCB nextPCB) {
        CPUState state = nextPCB.getCpuState();
        this.currentPCB = nextPCB;
        this.programCounter = currentPCB.getCurrentProgramCounter();
        if (state != null) {
            this.registries = state.getRegisters();
        } else {
            this.registries = new int[REGISTER_LENGTH];
        }
    }

    public static class Register {
        public static final int R1 = 0;
        public static final int R2 = 1;
        public static final int R3 = 2;
        public static final int R4 = 3;
        public static final int R5 = 4;
        public static final int R6 = 5;
        public static final int R7 = 6;
        public static final int R8 = 7;
        public static final int R9 = 8;
        public static final int ANY = -1;
    }
}
