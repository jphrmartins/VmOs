package vm.programs;

import static vm.CPU.Register.ANY;
import static vm.CPU.Register.R1;

import vm.Opcode;
import vm.Word;

public class ProgMinimo implements Program {

    @Override
    public Word[] createProgram() {
        return new Word[]{
                new Word(Opcode.LDI, R1, ANY, 999),
                new Word(Opcode.STD, R1, ANY, 10),
                new Word(Opcode.STD, R1, ANY, 11),
                new Word(Opcode.STD, R1, ANY, 12),
                new Word(Opcode.STD, R1, ANY, 13),
                new Word(Opcode.STD, R1, ANY, 14),
                Word.stop(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord()
        };
    }
}
