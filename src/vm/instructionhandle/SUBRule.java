package vm.instructionhandle;

import vm.CPU;
import vm.InstructionLoop;
import vm.Opcode;
import vm.Word;

public class SUBRule implements InstructionLoop {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.SUB;
    }

    @Override
    public void executeInstruction(CPU cpu, Word instruction) {

    }
}
