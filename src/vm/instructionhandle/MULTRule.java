package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class MULTRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.MULT;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = cpu.getReg()[instruction.getR1()] * cpu.getReg()[instruction.getR2()];
        cpu.incrementPc();
    }
}
