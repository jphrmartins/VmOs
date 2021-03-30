package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;

public class STXRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STX;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        if(cpu.getRegistries()[instruction.getR1()] < 0 || cpu.getRegistries()[instruction.getR1()] > cpu.getMemory().length){
            return new MemoryOutOfBoundsInterruption(cpu.getRegistries()[instruction.getR1()], cpu.getMemory().length);
        }
        cpu.getMemory()[cpu.getRegistries()[instruction.getR1()]] = Word.newData(cpu.getRegistries()[instruction.getR2()]);
        cpu.incrementPc();
        return null;
    }
}
