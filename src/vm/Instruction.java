package vm;

public interface Instruction {

    boolean shouldExecute(Opcode opcode);

    void executeInstruction(CPU cpu, Word instruction);
}
