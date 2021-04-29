package vm.programs;

import vm.Opcode;
import vm.Word;

import static vm.CPU.Register.*;

public class Factorial implements Program {
    @Override
    public Word[] createProgram() {
        return new Word[]{
                new Word(Opcode.LDI, R1, ANY, 5), // poe valor no r1												1 		r1 =5
                new Word(Opcode.STD, R1, ANY, 29), // memoria[29] = r1                                           2
                new Word(Opcode.LDD, R1, ANY, 29), // r1 = memoria[29]                                          3
                new Word(Opcode.LDI, R2, ANY, 18), // poe valor de pc para por o r1 na memoria de saida no r2	4		r2 = posicao de saida

                new Word(Opcode.JMPIL, R2, 1, -1), // se r1<0 pula pra pc de r2, se nao continua						se r1<0, vai para a posicao de r2

                new Word(Opcode.STD, R1, ANY, 30), // coloca o q ta no r1 na memoria 30									memoria[30] = r1
                new Word(Opcode.LDI, R5, ANY, 30),  //poe em r5 a posicao da memoria										r5 = 30
                new Word(Opcode.LDX, R2, R5, -1), // r2 = m[30]															r2 = memoria[r5]
                new Word(Opcode.SUBI, R2, ANY, 1), //r2=r2-1																r2 = r2-1 => fatorial(5 *(5-1))
                new Word(Opcode.LDI, R4, ANY, 10), // coloca no r4 o pc do inicio da multiplicacao						r4 = a posicao onde comeca o multiplicador
                // multiplica
                new Word(Opcode.MULT, R1, R2, -1), // multiplica r1,r2													r1= r*2
                new Word(Opcode.SUBI, R2, ANY, 1), // r2=r2-1															r2--
                new Word(Opcode.JMPIG, R4, R2, -1), // se r2>0 entao pula pra r4, no inicio da multi						if(r2>0) go to r4 (MULT)

                new Word(Opcode.LDI, R4, ANY, 18), // coloca no r4 o pc do fim											r4 = posicao do final
                new Word(Opcode.STD, R1, ANY, 23), // coloca o q ta em r1 em 30											MEMORIA[30] = r1
                new Word(Opcode.JMPIE, R4, R3, -1), // se r3==0 entao pula pra r4, no inicio da multi   					r3 =0 , if(r3==0) pula pro final (r4)
                new Word(Opcode.LDI, R1, ANY, -1), // coloca -1 em r1
                //final
                new Word(Opcode.STD, R1, ANY, 23), // armazena na memoria 0 o valor do r1, no caso -1					MEMORIA[30] = r1
                Word.stop(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
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
