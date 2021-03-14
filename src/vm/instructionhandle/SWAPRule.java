package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class SWAPRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.SWAP;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        int aux = cpu.getReg()[instruction.getR1()];
        cpu.getReg()[instruction.getR1()] = cpu.getReg()[instruction.getR2()];
        cpu.getReg()[instruction.getR2()] = aux;
        cpu.incrementPc();
    }
    
}
