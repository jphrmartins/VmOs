package vm.instructionhandle;

import vm.*;
import vm.interruptions.list.ProgramOutOfBoundsInterruption;
import vm.interruptions.SystemInterrupt;

import java.util.Optional;

public class JMPIRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPI;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        int registerValue = cpu.getRegistries()[instruction.getR1()];
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(registerValue);
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), registerValue);
        }
        cpu.setContext(memoryPosition.get());
        return null;
    }
}
