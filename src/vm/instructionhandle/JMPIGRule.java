package vm.instructionhandle;

import vm.*;
import vm.interruptions.ProgramOutOfBoundsInterruption;
import vm.interruptions.SystemInterrupt;

import java.util.Optional;

public class JMPIGRule implements InstructionRule {

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIG;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        int registerValue = cpu.getRegistries()[instruction.getR1()];
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(registerValue);
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), registerValue);
        }
        if (cpu.getRegistries()[instruction.getR2()] > 0) {
            cpu.setContext(memoryPosition.get());
        } else {
            cpu.incrementPc();
        }
        return null;
    }
}
