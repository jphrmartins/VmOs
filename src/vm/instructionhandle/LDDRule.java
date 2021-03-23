package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class LDDRule implements InstructionRule {

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDD;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getRegistries()[instruction.getR1()] = cpu.getMemory()[instruction.getP()].getP();
        cpu.incrementPc();
        return null;
    }
    
}
