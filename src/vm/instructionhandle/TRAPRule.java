package vm.instructionhandle;

import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;
import vm.interruptions.SystemInterrupt;

public class TRAPRule implements InstructionRule{

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.TRAP;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.incrementPc();
        cpu.getCurrentPCB().setBlocked(true);
        return null;
    }
    
}
