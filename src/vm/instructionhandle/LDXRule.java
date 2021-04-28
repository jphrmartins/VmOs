package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.MemoryOutOfBoundsInterruption;
import vm.interruptions.list.ProgramOutOfBoundsInterruption;

import java.util.Optional;

public class LDXRule implements InstructionRule {

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDX;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        int registerValue = cpu.getRegistries()[instruction.getR2()];
        if(cpu.getRegistries()[instruction.getR2()] < 0 || cpu.getRegistries()[instruction.getR2()] > cpu.getMemory().length){
            return new MemoryOutOfBoundsInterruption(cpu.getRegistries()[instruction.getR2()], cpu.getMemory().length);
        }
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(registerValue);
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), registerValue);
        }
        cpu.getRegistries()[instruction.getR1()] = cpu.getMemory()[registerValue].getP();
        cpu.incrementPc();
        return null;
    }
    

}
