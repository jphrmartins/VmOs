package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class STDRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STD;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getMemory()[instruction.getP()] = Word.newData(cpu.getRegistries()[instruction.getR1()]);
        cpu.incrementPc();
        return null;
    }
}
