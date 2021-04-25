package vm.instructionhandle;

import vm.*;
import vm.interruptions.ProgramOutOfBoundsInterruption;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;

import java.util.Optional;

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
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(instruction.getP());
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), instruction.getP());
        }
        cpu.getRegistries()[instruction.getR1()] = cpu.getMemory()[memoryPosition.get()].getP();
        cpu.incrementPc();
        return null;
    }
    
}
