package vm.instructionhandle;

import vm.CPU;
import vm.Instruction;
import vm.Opcode;
import vm.Word;

public class ADDRule implements Instruction {
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
