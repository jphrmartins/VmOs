package vm.instructionhandle;

import vm.*;
import vm.interruptions.ProgramOutOfBoundsInterruption;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;

import java.util.Optional;

public class STXRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STX;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        int registerValue = cpu.getRegistries()[instruction.getR1()];
        if(registerValue < 0 || registerValue > cpu.getMemory().length){
            return new MemoryOutOfBoundsInterruption(cpu.getRegistries()[instruction.getR1()], cpu.getMemory().length);
        }
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(registerValue);
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), registerValue);
        }
        cpu.getMemory()[memoryPosition.get()] = Word.newData(cpu.getRegistries()[instruction.getR2()]);
        cpu.incrementPc();
        return null;
    }
}
