package vm.programs;

import vm.Opcode;
import vm.Word;

import static vm.CPU.Register.*;

public class BubbleSort implements Program {
    @Override
    public Word[] createProgram() {
        return new Word[]{
                new Word(Opcode.LDI, R1, ANY, 15),
                new Word(Opcode.STD, R1, ANY, 60), //inicio do vetor na memoria[60]
                new Word(Opcode.LDI, R1, ANY, 7),
                new Word(Opcode.STD, R1, ANY, 61),
                new Word(Opcode.LDI, R1, ANY, 3),
                new Word(Opcode.STD, R1, ANY, 62),
                new Word(Opcode.LDI, R1, ANY, 9),
                new Word(Opcode.STD, R1, ANY, 63),
                new Word(Opcode.LDI, R1, ANY, 1),
                new Word(Opcode.STD, R1, ANY, 64), // fim do vetor na memoria[64]

                new Word(Opcode.LDI, R1, ANY, 60), // marcador do inicio do vetor
                new Word(Opcode.STD, R1, ANY, 50), // salvando o inicio em memoria[50]
                new Word(Opcode.STD, R1, ANY, 51), // i = inicio do vetor em memoria[51]
                new Word(Opcode.STD, R1, ANY, 52), // j = inicio do vetor em memoria[52]
                new Word(Opcode.LDI, R1, ANY, 61), // colocando j + 1 no registrador[1]
                new Word(Opcode.STD, R1, ANY, 53), // salvando j + 1 em memoria[53]
                new Word(Opcode.LDI, R1, ANY, 65), // setando fim do vetor no registrador[1]
                new Word(Opcode.STD, R1, ANY, 54), // marcador do a posicao final do vetor em memoria[54]

                new Word(Opcode.LDD, R1, ANY, 51), //iniciando "i" com a posicao inicial do vet
                //loop_i
                new Word(Opcode.LDD, R2, ANY, 52), //iniciando "j" com a posicao inicial do vet
                new Word(Opcode.LDD, R3, ANY, 53), //iniciando "j + 1" com a posicao inicial + 1 do vet
                //loop_j
                new Word(Opcode.LDX, R4, R2, -1), //pegando da memoria o valor MEM[J]
                new Word(Opcode.LDX, R5, R3, -1), //pegando da memoria o valor MEM[J + 1]
                new Word(Opcode.SUB, R4, R5, -1), //Mem[J] - Mem[J + 1] (ex 5 - 4 = 1 (troca), 4 - 5 = -1 )
                //if
                new Word(Opcode.LDI, R5, ANY, 31), //carregando r5 com o PC do skip (32), caso n precise trocar (skip)
                new Word(Opcode.JMPIL, R5, R4, -1),  //Se MEM[J] - MEM[J + 1] > 0 troca; se não vai pro (skip);
                //corpo do if
                new Word(Opcode.LDX, R4, R2, -1), //pegando da memoria o valor MEM[J]
                new Word(Opcode.LDX, R5, R3, -1), //pegando da memoria o valor MEM[J + 1]
                new Word(Opcode.SWAP, R4, R5, -1), //trocando os valores nos registradores
                new Word(Opcode.STX, R2, R4, -1), //armazenando o novo valor em MEM[J]
                new Word(Opcode.STX, R3, R5, -1), //armazenando o novo valor em MEM[J + 1]
                //skip
                new Word(Opcode.ADDI, R2, ANY, 1), //J++
                new Word(Opcode.ADDI, R3, ANY, 1), //J++++
                new Word(Opcode.LDD, R4, ANY, 54), //pegando da memoria a ultima posicao do vetor
                new Word(Opcode.SUB, R4, R3, -1), //fazendo tamanhoVetor - (J + 1) se R4 > 0 loop_j
                new Word(Opcode.LDI, R5, ANY, 21), //carregando com PC do loop_J
                new Word(Opcode.JMPIG, R5, R4, -1), //r4 > 0? pula pra loop_j : continua;
                //fim do loop_j
                new Word(Opcode.ADDI, R1, ANY, 1), //i++
                new Word(Opcode.LDD, R4, ANY, 54), //pegando da memoria a ultima posicao do vetor
                new Word(Opcode.SUB, R4, R1, -1), //fazendo tamanhoVetor - i se R4 > 0 loop_i
                new Word(Opcode.LDI, R5, ANY, 19), //carregando com PC do loop_i
                new Word(Opcode.JMPIG, R5, R4, -1), //R4 > 0? pula pra loop_i : continua;
                //fim do loop_i
                new Word(Opcode.STOP, ANY, ANY, -1),
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
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord(),
                Word.emptyWord()
        };
    }
}
