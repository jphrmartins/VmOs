package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class LDIRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDI;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = instruction.getP();
        cpu.incrementPc();
    }
}
