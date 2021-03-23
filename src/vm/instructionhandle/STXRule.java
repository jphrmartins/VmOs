package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class STXRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STX;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getMemory()[cpu.getRegistries()[instruction.getR1()]] = Word.newData(cpu.getRegistries()[instruction.getR2()]);
        cpu.incrementPc();
        return null;
    }
}
