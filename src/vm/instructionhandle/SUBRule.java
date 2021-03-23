package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class SUBRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.SUB;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getRegistries()[instruction.getR1()] = cpu.getRegistries()[instruction.getR1()] - cpu.getRegistries()[instruction.getR2()];
        cpu.incrementPc();
        return null;
    }
}
