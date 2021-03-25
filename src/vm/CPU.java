package vm;

import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.InvalidRuleInterruption;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;

import java.util.Set;

public class CPU {
    private int programCounter;             // ... composto de program counter,
    private final SystemOperational systemOperational; //Pointer to the sysops
    private final int[] registries; //Registradores
    private final Word[] memory;   //vm.CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    private final Set<InstructionRule> instructionRules;

    public CPU(SystemOperational systemOperational, Word[] memory, Set<InstructionRule> instructionRules) {     // ref a MEMORIA e interrupt handler passada na criacao da vm.CPU
        this.memory = memory;                // usa o atributo 'm' para acessar a memoria.
        registries = new int[8];     // aloca o espaço dos registradores
        this.instructionRules = instructionRules;
        this.systemOperational = systemOperational;
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

    public void setContext(int pc) {  // no futuro esta funcao vai ter que ser
        this.programCounter = pc;                                              // limite e pc (deve ser zero nesta versao)
    }

    public SystemOperational getSystemPointer() {
        return this.systemOperational;
    }

    public void incrementPc() {
        this.programCounter++;
    }

    public void run() {        // execucao da vm.CPU supoe que o contexto da vm.CPU, vide acima, esta devidamente setado
        // instruction register,
        Word instruction;
        while (true) { // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            SystemInterrupt interrupt = null;
            if (programCounter > memory.length) {
                interrupt = new MemoryOutOfBoundsInterruption(programCounter, memory.length);
            } else {
                instruction = memory[programCounter];    // busca posicao da memoria apontada por pc, guarda em instruction
                for (InstructionRule rule : instructionRules) {
                    if (rule.shouldExecute(instruction.getOpc())) {
                        interrupt = rule.executeRule(this, instruction);
                        break;
                    }
                }
                interrupt = new InvalidRuleInterruption(instruction.getOpc());
            }
            if (interrupt != null) {
                boolean shouldHalt = systemOperational.handleInterruption(this, interrupt);
                if (shouldHalt) break; // break sai do loop da cpu
            }
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
        public static final int ANY = -1;
    }
}
