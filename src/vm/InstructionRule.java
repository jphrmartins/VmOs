package vm;

public interface InstructionRule {

    boolean shouldExecute(Opcode opcode);

    void executeRule(CPU cpu, Word instruction);
}
