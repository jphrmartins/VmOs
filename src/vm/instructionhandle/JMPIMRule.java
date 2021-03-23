package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class JMPIMRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIM;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.setContext(cpu.getMemory()[instruction.getP()].getP());
        return null;
    }
}
