package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.StopInterruption;

public class STOPRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STOP;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        return new StopInterruption();
    }
}
