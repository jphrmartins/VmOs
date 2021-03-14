package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class JMPIMRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIM;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.setContext(cpu.getMemory()[instruction.getP()].getP());
    }
}
