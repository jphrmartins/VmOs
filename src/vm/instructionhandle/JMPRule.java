package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class JMPRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMP;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.setPc(instruction.getP());
    }
}
