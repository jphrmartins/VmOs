package vm;

public interface InstructionLoop {

    boolean shouldExecute(Opcode opcode);

    void executeInstruction(CPU cpu, Word instruction);
}
