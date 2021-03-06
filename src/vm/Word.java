package vm;

public class Word {    // cada posicao da memoria tem uma instrucao (ou um dado)
    private Opcode opc;    //
    private int r1;        // indice do primeiro registrador da operacao (Rs ou Rd cfe opcode na tabela)
    private int r2;        // indice do segundo registrador da operacao (Rc ou Rs cfe operacao)
    private int p;        // parametro para instrucao (k ou A cfe operacao), ou o dado, se opcode = DADO

    private Word() {
        this.opc = Opcode.NULL;
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

    public static Word stop() {
        return new Word(Opcode.STOP, CPU.Register.ANY, CPU.Register.ANY, -1);
    }

    public Word clone() {
        return new Word(this.opc, this.r1, this.r2, this.p);
    }

    public boolean isEmptyWord() {
        return this.opc == Opcode.NULL;
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

    @Override
    public String toString() {
        return "Word{opc=" + opc + ", r1=" + r1 + ", r2=" + r2 + ", p=" + p + '}';
    }
}
