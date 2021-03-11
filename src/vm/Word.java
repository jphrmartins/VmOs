package vm;

import vm.Opcode;

public class Word {    // cada posicao da memoria tem uma instrucao (ou um dado)
    public Opcode opc;    //
    public int r1;        // indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
    public int r2;        // indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
    public int p;        // parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

    public Word(Opcode opc, int r1, int r2, int p) {
        this.opc = opc;
        this.r1 = r1;
        this.r2 = r2;
        this.p = p;
    }
}