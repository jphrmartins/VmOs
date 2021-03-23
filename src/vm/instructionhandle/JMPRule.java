package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class JMPRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMP;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.setContext(instruction.getP());
        return null;
    }
}
