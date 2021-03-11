package vm;

public class CPU {
    // característica do processador: contexto da vm.CPU ...
    private int pc;            // ... composto de program counter,
    private Word ir;            // instruction register,
    private int[] reg;        // registradores da vm.CPU
    private Word[] memory;   // vm.CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.

    public CPU(Word[] memory) {     // ref a MEMORIA e interrupt handler passada na criacao da vm.CPU
        this.memory = memory;                // usa o atributo 'm' para acessar a memoria.
        reg = new int[8];        // aloca o espaço dos registradores
    }

    public void setContext(int pc) {  // no futuro esta funcao vai ter que ser
        this.pc = pc;                                              // limite e pc (deve ser zero nesta versao)
    }

    public void run() {        // execucao da vm.CPU supoe que o contexto da vm.CPU, vide acima, esta devidamente setado
        // break sai do loop da cpu
        do {            // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            // FETCH
            ir = memory[pc];    // busca posicao da memoria apontada por pc, guarda em ir
            // EXECUTA INSTRUCAO NO ir
            switch (ir.opc) { // para cada opcode, sua execução

                case Opcode.LDI: // Rd ← k
                    reg[ir.r1] = ir.p;
                    pc++;
                    break;

                case Opcode.STD: // [A] ← Rs
                    memory[ir.p].opc = Opcode.DATA;
                    memory[ir.p].p = reg[ir.r1];
                    pc++;
                    break;

                case Opcode.ADD: // Rd ← Rd + Rs
                    reg[ir.r1] = reg[ir.r1] + reg[ir.r2];
                    pc++;
                    break;

                case Opcode.ADDI: // Rd ← Rd + k
                    reg[ir.r1] = reg[ir.r1] + ir.p;
                    pc++;
                    break;

                case Opcode.STX: // [Rd] ←Rs
                    memory[reg[ir.r1]].opc = Opcode.DATA;
                    memory[reg[ir.r1]].p = reg[ir.r2];
                    pc++;
                    break;

                case Opcode.SUB: // Rd ← Rd - Rs
                    reg[ir.r1] = reg[ir.r1] - reg[ir.r2];
                    pc++;
                    break;

                case Opcode.JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1
                    if (reg[ir.r2] > 0) {
                        pc = reg[ir.r1];
                    } else {
                        pc++;
                    }
                    break;

                case Opcode.STOP: // por enquanto, para execucao
                    break;
            }
        } while (ir.opc != Opcode.STOP); // VERIFICA INTERRUPÇÃO !!! - TERCEIRA FASE DO CICLO DE INSTRUÇÕES
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
