package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.ProgramOutOfBoundsInterruption;

import java.util.Optional;

public class JMPIERule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIE;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        int registerValue = cpu.getRegistries()[instruction.getR1()];
        Optional<Integer> memoryPosition = cpu.getCurrentPCB().getMemoryPosition(registerValue);
        if (memoryPosition.isEmpty()) {
            return new ProgramOutOfBoundsInterruption(cpu.getCurrentPCB(), registerValue);
        }
        if (cpu.getRegistries()[instruction.getR2()] == 0) {
            cpu.setContext(memoryPosition.get());
        } else {
            cpu.incrementPc();
        }
        return null;
    }
}
