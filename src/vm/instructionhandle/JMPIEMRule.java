package vm.instructionhandle;

import vm.*;
import vm.interruptions.list.ProgramOutOfBoundsInterruption;
import vm.interruptions.SystemInterrupt;

import java.util.Optional;

public class JMPIEMRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIEM;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(instruction.getP());
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), instruction.getP());
        }
        if (cpu.getRegistries()[instruction.getR2()] == 0) {
            cpu.setContext(cpu.getMemory()[memoryPosition.get()].getP());
        } else {
            cpu.incrementPc();
        }
        return null;
    }
}
