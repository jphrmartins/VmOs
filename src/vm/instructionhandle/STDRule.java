package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;

public class STDRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STD;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        if(instruction.getP() < 0 || instruction.getP() > cpu.getMemory().length){
            return new MemoryOutOfBoundsInterruption(instruction.getP(), cpu.getMemory().length);
        }
        cpu.getMemory()[instruction.getP()] = Word.newData(cpu.getRegistries()[instruction.getR1()]);
        cpu.incrementPc();
        return null;
    }
}
