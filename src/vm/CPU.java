package vm;

public class CPU {
    // característica do processador: contexto da vm.CPU ...
    private int pc;             // ... composto de program counter,
    private final int[] reg;        // registradores da vm.CPU
    private final Word[] memory;   // vm.CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.

    public CPU(Word[] memory) {     // ref a MEMORIA e interrupt handler passada na criacao da vm.CPU
        this.memory = memory;                // usa o atributo 'm' para acessar a memoria.
        reg = new int[8];        // aloca o espaço dos registradores
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

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void run() {        // execucao da vm.CPU supoe que o contexto da vm.CPU, vide acima, esta devidamente setado
        // break sai do loop da cpu
        // instruction register,
        Word instruction;
        do {            // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            // FETCH
            instruction = memory[pc];    // busca posicao da memoria apontada por pc, guarda em instruction
            // EXECUTA INSTRUCAO NO instruction
            switch (instruction.getOpc()) { // para cada opcode, sua execução
                case LDI: // Rd ← k
                    reg[instruction.getR1()] = instruction.getP();
                    pc++;
                    break;

                case STD: // [A] ← Rs
                    memory[instruction.getP()] = Word.newData(reg[instruction.getR1()]);
                    pc++;
                    break;

                case ADD: // Rd ← Rd + Rs
                    reg[instruction.getR1()] = reg[instruction.getR1()] + reg[instruction.getR2()];
                    pc++;
                    break;

                case ADDI: // Rd ← Rd + k
                    reg[instruction.getR1()] = reg[instruction.getR1()] + instruction.getP();
                    pc++;
                    break;

                case STX: // [Rd] ←Rs
                    memory[reg[instruction.getR1()]] = Word.newData(reg[instruction.getR1()]);
                    pc++;
                    break;

                case SUB: // Rd ← Rd - Rs
                    reg[instruction.getR2()] = reg[instruction.getR1()] - reg[instruction.getR2()];
                    pc++;
                    break;

                case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1
                    if (reg[instruction.getR2()] > 0) {
                        pc = reg[instruction.getR1()];
                    } else {
                        pc++;
                    }
                    break;

                case STOP: // por enquanto, para execucao
                    break;
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
