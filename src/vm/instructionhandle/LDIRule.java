package vm.instructionhandle;

import vm.CPU;
import vm.InstructionLoop;
import vm.Opcode;
import vm.Word;

public class LDIRule implements InstructionLoop {
    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDI;
    }

    @Override
    public void executeInstruction(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = instruction.getP();
        cpu.setPc(cpu.getPc() + 1);
    }
}
