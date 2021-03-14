package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class STXRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STX;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.getMemory()[cpu.getReg()[instruction.getR1()]] = Word.newData(cpu.getReg()[instruction.getR1()]);
        cpu.incrementPc();
    }
}
