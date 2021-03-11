package vm;

import vm.Opcode;

public class Word {    // cada posicao da memoria tem uma instrucao (ou um dado)
    private Opcode opc;    //
    private int r1;        // indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
    private int r2;        // indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
    private int p;        // parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

    private Word() {
    }

    public Word(Opcode opc, int r1, int r2, int p) {
        this.opc = opc;
        this.r1 = r1;
        this.r2 = r2;
        this.p = p;
    }

    public static Word newData(int p) {
        return new Word(Opcode.DATA, CPU.Register.ANY, CPU.Register.ANY, p);
    }

    public static Word emptyWord() {
        return new Word();
    }

    public Word clone() {
        return new Word(this.opc, this.r1, this.r2, this.p);
    }


    public Opcode getOpc() {
        return opc;
    }

    public int getR1() {
        return r1;
    }

    public int getR2() {
        return r2;
    }

    public int getP() {
        return p;
    }

}
