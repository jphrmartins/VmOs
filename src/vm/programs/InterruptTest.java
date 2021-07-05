package vm.programs;

import vm.Opcode;
import vm.Word;

import static vm.CPU.Register.ANY;
import static vm.CPU.Register.R1;

public class InterruptTest extends Program {
    public InterruptTest() {
        super("Interrupt", 2);
    }

    @Override
    public Word[] loadProgramWords() {
        return new Word[]{
                new Word(Opcode.LDI, R1, ANY, 0),
                new Word(Opcode.STD, R1, ANY, 10),
                new Word(Opcode.JMP, ANY, ANY, 9999),
                new Word(Opcode.STD, R1, ANY, 11),
                Word.stop(),
        };
    }
}
