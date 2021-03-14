package vm.instructionhandle;

import vm.CPU;
import vm.InstructionLoop;
import vm.Opcode;
import vm.Word;

public class ADDRule implements InstructionLoop {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.ADD;
    }

    @Override
    public void executeInstruction(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = cpu.getReg()[instruction.getR1()] + cpu.getReg()[instruction.getR2()];
        cpu.incrementPc();
    }
}
