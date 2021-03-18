package vm;

import java.util.Set;

public class CPU {
    // característica do processador: contexto da vm.CPU ...
    private int pc;             // ... composto de program counter,
    private final int[] reg;        // registradores da vm.CPU
    private final Word[] memory;   // vm.CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    private final Set<InstructionRule> instructionRules;

    public CPU(Word[] memory, Set<InstructionRule> instructionRules) {     // ref a MEMORIA e interrupt handler passada na criacao da vm.CPU
        this.memory = memory;                // usa o atributo 'm' para acessar a memoria.
        reg = new int[8];     // aloca o espaço dos registradores
        this.instructionRules = instructionRules;
    }

    public int getPc() {
        return pc;
    }

    public int[] getReg() {
        return reg;
    }

    public Word[] getMemory() {
        return memory;
    }

    public void setContext(int pc) {  // no futuro esta funcao vai ter que ser
        this.pc = pc;                                              // limite e pc (deve ser zero nesta versao)
    }

    public void incrementPc() {
        this.pc++;
    }

    public void run() {        // execucao da vm.CPU supoe que o contexto da vm.CPU, vide acima, esta devidamente setado
        // break sai do loop da cpu
        // instruction register,
        Word instruction;
        do {            // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            // FETCH
            instruction = memory[pc];    // busca posicao da memoria apontada por pc, guarda em instruction
            if (instruction.getOpc() != Opcode.STOP) {
                for (InstructionRule rule : instructionRules) {
                    if (rule.shouldExecute(instruction.getOpc())) {
                        rule.executeRule(this, instruction);
                        break;
                    }
                }
            }

        } while (instruction.getOpc() != Opcode.STOP); // VERIFICA INTERRUPÇÃO !!! - TERCEIRA FASE DO CICLO DE INSTRUÇÕES
        //Mudei por que o intellij falou para.... :madeByJp:
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
