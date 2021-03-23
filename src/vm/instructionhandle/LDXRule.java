package vm.instructionhandle;
import vm.*;
import vm.interruptions.SystemInterrupt;

public class LDXRule implements InstructionRule {

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDX;
    }

    @Override
    public SystemInterrupt executeRule(CPU cpu, Word instruction) {
        cpu.getRegistries()[instruction.getR1()] = cpu.getMemory()[cpu.getRegistries()[instruction.getR2()]].getP();
        cpu.incrementPc();
        return null;
    }
    

}
