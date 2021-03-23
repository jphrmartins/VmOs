package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.InvalidRuleInterruption;

public class InvalidRule implements InstructionRule {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.NULL || opcode == Opcode.DATA;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        if (instruction.getOpc() == Opcode.NULL) {
            return new InvalidRuleInterruption(Opcode.NULL);
        }
        return new InvalidRuleInterruption(Opcode.DATA);
    }
}
