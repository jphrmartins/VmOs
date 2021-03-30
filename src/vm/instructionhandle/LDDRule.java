package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;

public class LDDRule implements InstructionRule {

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDD;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        if(instruction.getP() < 0 || instruction.getP() > cpu.getMemory().length){
            return new MemoryOutOfBoundsInterruption(instruction.getP(), cpu.getMemory().length);
        }

        cpu.getRegistries()[instruction.getR1()] = cpu.getMemory()[instruction.getP()].getP();
        cpu.incrementPc();
        return null;
    }
    
}
