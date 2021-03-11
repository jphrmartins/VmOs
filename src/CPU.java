public class CPU {
    // característica do processador: contexto da CPU ...
    private int pc;            // ... composto de program counter,
    private Word ir;            // instruction register,
    private int[] reg;        // registradores da CPU
    private Word[] memory;   // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.

    public CPU(Word[] memory) {     // ref a MEMORIA e interrupt handler passada na criacao da CPU
        this.memory = memory;                // usa o atributo 'm' para acessar a memoria.
        reg = new int[8];        // aloca o espaço dos registradores
    }

    public void setContext(int pc) {  // no futuro esta funcao vai ter que ser
        this.pc = pc;                                              // limite e pc (deve ser zero nesta versao)
    }

    public void run() {        // execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
        // break sai do loop da cpu
        do {            // ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            // FETCH
            ir = memory[pc];    // busca posicao da memoria apontada por pc, guarda em ir
            // EXECUTA INSTRUCAO NO ir
            switch (ir.opc) { // para cada opcode, sua execução

                case LDI: // Rd ← k
                    reg[ir.r1] = ir.p;
                    pc++;
                    break;

                case STD: // [A] ← Rs
                    memory[ir.p].opc = Opcode.DATA;
                    memory[ir.p].p = reg[ir.r1];
                    pc++;
                    break;

                case ADD: // Rd ← Rd + Rs
                    reg[ir.r1] = reg[ir.r1] + reg[ir.r2];
                    pc++;
                    break;

                case ADDI: // Rd ← Rd + k
                    reg[ir.r1] = reg[ir.r1] + ir.p;
                    pc++;
                    break;

                case STX: // [Rd] ←Rs
                    memory[reg[ir.r1]].opc = Opcode.DATA;
                    memory[reg[ir.r1]].p = reg[ir.r2];
                    pc++;
                    break;

                case SUB: // Rd ← Rd - Rs
                    reg[ir.r1] = reg[ir.r1] - reg[ir.r2];
                    pc++;
                    break;

                case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1
                    if (reg[ir.r2] > 0) {
                        pc = reg[ir.r1];
                    } else {
                        pc++;
                    }
                    break;

                case STOP: // por enquanto, para execucao
                    break;
            }
        } while (ir.opc != Opcode.STOP); // VERIFICA INTERRUPÇÃO !!! - TERCEIRA FASE DO CICLO DE INSTRUÇÕES
    }
}
