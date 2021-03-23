package vm;

import vm.interruptions.SystemInterrupt;

public interface InstructionRule {

    boolean shouldExecute(Opcode opcode);

    SystemInterrupt executeRule(CPU cpu, Word instruction);
}
