package vm.interruptions.list;

import vm.Opcode;
import vm.interruptions.SystemInterrupt;

public class InvalidRuleInterruption implements SystemInterrupt {
    private final Opcode opcode;
    public InvalidRuleInterruption(Opcode opcode) {
        this.opcode = opcode;
    }

    @Override
    public String getReason() {
        return "Should not call word instruction of type: " + opcode.name();
    }

    @Override
    public boolean shouldHalt() {
        return true;
    }
}
