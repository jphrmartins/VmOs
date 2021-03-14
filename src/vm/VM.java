package vm;

import vm.instructionhandle.*;

import java.util.*;

// ------------------- V M  - constituida de vm.CPU e MEMORIA -----------------------------------------------
// -------------------------- atributos e construcao da vm.VM -----------------------------------------------
public class VM {
    public int tamMem;
    public Word[] m;
    public CPU cpu;

    public VM() {
        // memória
        tamMem = 1024;
        m = new Word[tamMem]; // m ee a memoria
        for (int i = 0; i < tamMem; i++) {
            m[i] = Word.emptyWord();
        }
        ;
        // cpu
        Set<InstructionRule> instructionRules = new HashSet<>();
        Collections.addAll(instructionRules, new ADDIRule(), new ADDRule(), new JMPIGRule(), new LDIRule(),
                new STDRule(), new STXRule(), new SUBRule());
        cpu = new CPU(m, instructionRules);
    }
}
