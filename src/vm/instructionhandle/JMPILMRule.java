package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class JMPILMRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPILM;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        if (cpu.getRegistries()[instruction.getR2()] < 0) {
            cpu.setContext(cpu.getMemory()[instruction.getP()].getP());
        } else {
            cpu.incrementPc();
        }
        return null;
    }
}
