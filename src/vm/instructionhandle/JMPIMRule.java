package vm.instructionhandle;

import vm.*;
import vm.interruptions.ProgramOutOfBoundsInterruption;
import vm.interruptions.SystemInterrupt;

import java.util.Optional;

public class JMPIMRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIM;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(instruction.getP());
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), instruction.getP());
        }
        cpu.setContext(cpu.getMemory()[memoryPosition.get()].getP());
        return null;
    }
}
