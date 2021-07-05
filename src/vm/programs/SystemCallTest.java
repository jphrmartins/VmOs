package vm.programs;

import vm.Opcode;
import vm.Word;

import static vm.CPU.Register.*;

public class SystemCallTest extends Program {
    public SystemCallTest() {
        super("TrapTest", 1);
    }

    @Override
    public Word[] loadProgramWords() {
        return new Word[]{
                new Word(Opcode.LDI, R8, ANY, 1),
                new Word(Opcode.TRAP, R8, R9, -1),
                new Word(Opcode.LDI, R8, ANY, 2),
                new Word(Opcode.TRAP, R8, R9, -1),
                Word.stop()
        };
    }
}
