package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class LDDRule implements InstructionRule {

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDD;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = cpu.getMemory()[instruction.getP()].getP();
        cpu.incrementPc();
    }
    
}
