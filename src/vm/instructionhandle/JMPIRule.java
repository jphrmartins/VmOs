package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class JMPIRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPI;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.setContext(cpu.getRegistries()[instruction.getR1()]);
        return null;
    }
}
