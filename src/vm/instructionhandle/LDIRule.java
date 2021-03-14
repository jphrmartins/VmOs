package vm.instructionhandle;

import vm.CPU;
import vm.Instruction;
import vm.Opcode;
import vm.Word;

public class LDIRule implements Instruction {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDI;
    }

    @Override
    public void executeInstruction(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = instruction.getP();
        cpu.incrementPc();
    }
}
