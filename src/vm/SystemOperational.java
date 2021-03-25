package vm;

import vm.instructionhandle.*;
import vm.interruptions.InterruptionHandler;
import vm.interruptions.SystemInterrupt;

import java.util.*;

// ------------------- V M  - constituida de vm.CPU e MEMORIA -----------------------------------------------
// -------------------------- atributos e construcao da vm.VM -----------------------------------------------
public class SystemOperational {
    public int tamMem;
    public Word[] m;
    public CPU cpu;
    public InterruptionHandler interruptionHandler;

    public SystemOperational() {
        tamMem = 1024;
        m = new Word[tamMem];
        for (int i = 0; i < tamMem; i++) {
            m[i] = Word.emptyWord();
        }
        this.interruptionHandler = new InterruptionHandler();
        Set<InstructionRule> instructionRules = new HashSet<>();
        Collections.addAll(instructionRules, new JMPRule(), new JMPIRule(), new JMPIGRule(), new JMPILRule(),
                new JMPIERule(), new JMPIMRule(), new JMPIGMRule(), new JMPILMRule(), new JMPIEMRule(),
                new ADDIRule(), new SUBIRule(), new ADDRule(), new SUBRule(), new MULTRule(), new LDIRule(),
                new LDDRule(), new STDRule(), new LDXRule(), new STXRule(), new SWAPRule(), new STOPRule(), new InvalidRule()
        );
        cpu = new CPU(this, m, instructionRules);
    }

    public boolean handleInterruption(CPU cpu, SystemInterrupt interrupt) {
        return this.interruptionHandler.handleInterruption(cpu, interrupt);
    }
}
