package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class JMPIRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPI;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.setContext(cpu.getReg()[instruction.getR1()]);
    }
}
