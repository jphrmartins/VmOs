package vm.programs;

import vm.Opcode;
import vm.Word;

import static vm.CPU.Register.*;

public class SystemCallTest implements Program {
    @Override
    public Word[] createProgram() {
        return new Word[]{
                new Word(Opcode.LDI, R8, ANY, 1),
                new Word(Opcode.TRAP, R8, R9, -1),
                new Word(Opcode.LDI, R8, ANY, 2),
                new Word(Opcode.TRAP, R8, R9, -1)
        };
    }
}
