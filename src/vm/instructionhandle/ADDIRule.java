package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class ADDIRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.ADDI;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getRegistries()[instruction.getR1()] = cpu.getRegistries()[instruction.getR1()] + instruction.getP();
        cpu.incrementPc();
        return null;
    }
}
