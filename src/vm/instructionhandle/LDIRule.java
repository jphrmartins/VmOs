package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class LDIRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDI;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getRegistries()[instruction.getR1()] = instruction.getP();
        cpu.incrementPc();
        return null;
    }
}
