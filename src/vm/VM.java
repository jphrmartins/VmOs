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
        Collections.addAll(instructionRules, new JMPRule(), new JMPIRule(), new JMPIGRule(), new JMPILMRule(),
                new JMPIERule(), new JMPIMRule(), new JMPIGMRule(), new JMPILMRule(), new JMPIEMRule(),
                new ADDIRule(), new SUBIRule(), new ADDRule(), new SUBRule(), new MULTRule(), new LDIRule(),
                new LDDRule(), new STDRule(), new LDXRule(), new STXRule(), new SWAPRule()
        );
        cpu = new CPU(m, instructionRules);
    }
}
