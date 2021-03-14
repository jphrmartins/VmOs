package vm.instructionhandle;

import vm.CPU;
import vm.InstructionLoop;
import vm.Opcode;
import vm.Word;

public class STXRule implements InstructionLoop {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.STX;
    }

    @Override
    public void executeInstruction(CPU cpu, Word instruction) {
        cpu.getMemory()[cpu.getReg()[instruction.getR1()]] = Word.newData(cpu.getReg()[instruction.getR1()]);
        cpu.incrementPc();
    }
}
