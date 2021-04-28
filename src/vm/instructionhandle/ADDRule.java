package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;
import vm.interruptions.SystemInterrupt;

public class ADDRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.ADD;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getRegistries()[instruction.getR1()] = cpu.getRegistries()[instruction.getR1()] + cpu.getRegistries()[instruction.getR2()];
        cpu.incrementPc();
        return null;
    }
}
