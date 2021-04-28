package vm.interruptions.list;

import vm.Opcode;
import vm.interruptions.HaltInterruption;

public class InvalidRuleInterruption extends HaltInterruption {
    public InvalidRuleInterruption(Opcode opcode) {
        super("Should not call word instruction of type: " + opcode.name());
    }
}
