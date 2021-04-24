package vm.programs;

import vm.Opcode;
import vm.Word;

import static vm.CPU.Register.ANY;
import static vm.CPU.Register.R1;

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
                new Word(Opcode.STOP, ANY, ANY, -1)
        };
    }
}
