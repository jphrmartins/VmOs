package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class JMPILRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIL;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        if (cpu.getRegistries()[instruction.getR2()] < 0) {
            cpu.setContext(cpu.getRegistries()[instruction.getR1()]);
        } else {
            cpu.incrementPc();
        }
        return null;
    }
}
