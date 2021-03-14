package vm.instructionhandle;

import vm.CPU;
import vm.Instruction;
import vm.Opcode;
import vm.Word;

public class ADDIRule implements Instruction {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.ADDI;
    }

    @Override
    public void executeInstruction(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = cpu.getReg()[instruction.getR1()] + instruction.getP();
        cpu.incrementPc();
    }
}
