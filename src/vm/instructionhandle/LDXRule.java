package vm.instructionhandle;
import vm.CPU;
import vm.InstructionRule;
import vm.Opcode;
import vm.Word;

public class LDXRule implements InstructionRule {

    @Override
    public boolean shouldExecute(Opcode opcode) {
        return opcode == Opcode.LDX;
    }

    @Override
    public void executeRule(CPU cpu, Word instruction) {
        cpu.getReg()[instruction.getR1()] = cpu.getMemory()[cpu.getReg()[instruction.getR2()]].getP();
        cpu.incrementPc();
    }
    

}
