package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class SUBIRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.SUBI;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = cpu.getReg()[instruction.getR1()] + instruction.getP();
        cpu.incrementPc();
    }
}
