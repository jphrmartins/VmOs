package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class JMPIM implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIM;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.setPc(cpu.getMemory()[instruction.getP()].getP());
    }
}
