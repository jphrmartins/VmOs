package vm.programs;

import vm.Opcode;
import vm.Word;

import static vm.CPU.Register.*;

public class Fibo extends Program {
    public Fibo() {
        super(6);
    }

    @Override
    public Word[] loadProgramWords() {
        return new Word[]{
                // Se quiser ver o fibonacci ate outro valor, altere o K na primeira linha (no caso o 10),  memoria de saida comeca no 50 e termina no 99
                new Word(Opcode.LDI, R2, ANY, 10), // poe valor no r1														r1 =5
                new Word(Opcode.STD, R2, ANY, 50), // memoria[50] = r1
                new Word(Opcode.LDD, R8, ANY, 50), // r7 = memoria[50] ---- r7 vai ser o contador
                new Word(Opcode.LDI, R1, ANY, 52), // r0=52
                new Word(Opcode.LDI, R5, ANY, -1), // r4=-1
                new Word(Opcode.LDI, R3, ANY, 27), // poe valor de pc para por o r1 na memoria de saida no r2			r2 = posicao de saida
                new Word(Opcode.JMPIL, R3, R2, -1), // se r1<0 pula pra pc de r2, se nao continua						se r1<0, vai para a posicao de r2
                //se >0
                new Word(Opcode.LDI, R2, ANY, 0),   // r1= 0
                new Word(Opcode.LDI, R3, ANY, 1),   // r2= 1
                new Word(Opcode.LDI, R7, ANY, 28), // r6=pc do final
                new Word(Opcode.STD, R2, ANY, 50), // memoria[60] = r1
                new Word(Opcode.SUBI, R8, ANY, 1),  // r7=r7-1
                new Word(Opcode.JMPIE, R7, R8, -1), // se r7==0 pc = r6
                new Word(Opcode.STD, R3, ANY, 51), // memoria[61] = r2
                new Word(Opcode.SUBI, R8, ANY, 1),  // r7=r7-1
                new Word(Opcode.JMPIE, R7, R8, -1), // se r7==0 pc = r6
                new Word(Opcode.LDI, R5, ANY, 16),  // r4 = 7
                new Word(Opcode.LDI, R4, ANY, 0),  // r3=0
                new Word(Opcode.ADD, R4, R2, -1),  // r3=r3+r1
                new Word(Opcode.LDI, R2, ANY, 0), // r1=0
                new Word(Opcode.ADD, R2, R3, -1), // r1=r1+r2
                new Word(Opcode.ADD, R3, R4, -1), // r2=r2+r3
                new Word(Opcode.STX, R1, R3, -1), // m[0] =r2
                new Word(Opcode.ADDI, R1, ANY, 1), // r0 = r0+1
                new Word(Opcode.SUBI, R8, ANY, 1),  // r7=r7-1
                new Word(Opcode.JMPIG, R5, R8, -1), // se r7>0 pc = r4
                new Word(Opcode.JMPIE, R7, R8, -1), // se r7==0 pc = r6
                new Word(Opcode.STD, R5, ANY, 50), // armazena na memoria 60 o valor do r4, no caso -1					MEMORIA[30] = r1
                Word.stop(),
        };
    }
}
