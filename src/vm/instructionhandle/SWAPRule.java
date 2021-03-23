package vm.instructionhandle;

import vm.*;
import vm.interruptions.SystemInterrupt;

public class SWAPRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.SWAP;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        int aux = cpu.getRegistries()[instruction.getR1()];
        cpu.getRegistries()[instruction.getR1()] = cpu.getRegistries()[instruction.getR2()];
        cpu.getRegistries()[instruction.getR2()] = aux;
        cpu.incrementPc();
        return null;
    }
    
}
