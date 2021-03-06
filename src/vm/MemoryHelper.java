package vm;

// -------------------------------------------  classes e funcoes auxiliares
public class MemoryHelper {
    public void dump(Word w) {
        System.out.print("[ ");
        System.out.print(w.getOpc());
        System.out.print(", ");
        System.out.print(w.getR1());
        System.out.print(", ");
        System.out.print(w.getR2());
        System.out.print(", ");
        System.out.print(w.getP());
        System.out.println("  ] ");
    }

    public void dump(Word[] m, int ini, int fim) {
        for (int i = ini; i < fim; i++) {
            System.out.print(i);
            System.out.print(":  ");
            dump(m[i]);
        }
    }
}
