package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class JMPIERule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIE;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        if (cpu.getReg()[instruction.getR2()] == 0) {
            cpu.setContext(cpu.getReg()[instruction.getR1()]);
        } else {
            cpu.incrementPc();
        }
    }
}
