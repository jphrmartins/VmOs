package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class STDRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STD;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.getMemory()[instruction.getP()] = Word.newData(cpu.getReg()[instruction.getR1()]);
        cpu.incrementPc();
    }
}
