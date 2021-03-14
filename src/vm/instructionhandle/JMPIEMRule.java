package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class JMPIEMRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.JMPIEM;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        if (cpu.getReg()[instruction.getR2()] == 0) {
            cpu.setPc(cpu.getMemory()[instruction.getP()].getP());
        } else {
            cpu.incrementPc();
        }
    }
}
