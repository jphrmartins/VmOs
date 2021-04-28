package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;
import vm.interruptions.list.ProgramOutOfBoundsInterruption;

import java.util.Optional;

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
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(instruction.getP());
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), instruction.getP());
        }
        cpu.getMemory()[memoryPosition.get()] = Word.newData(cpu.getRegistries()[instruction.getR1()]);
        cpu.incrementPc();
        return null;
    }
}
