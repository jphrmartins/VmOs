package vm;

import static vm.CPU.Register.*;

//  -------------------------------------------- programas aa disposicao para copiar na memoria (vide aux.carga)
public class Programas {
        public Word[] progMinimo = new Word[] { 
                new Word(Opcode.LDI, R1, ANY, 999), 
                new Word(Opcode.STD, R1, ANY, 10),
                new Word(Opcode.STD, R1, ANY, 11), 
                new Word(Opcode.STD, R1, ANY, 12),
                new Word(Opcode.STD, R1, ANY, 13), 
                new Word(Opcode.STD, R1, ANY, 14),
                new Word(Opcode.STOP, ANY, ANY, -1) 
        };

        public Word[] fibonacci10 = new Word[] { // mesmo que prog exemplo, so que usa r0 no lugar de r8
                new Word(Opcode.LDI, R2, ANY, 0),
                new Word(Opcode.STD, R2, ANY, 20), // 50
                new Word(Opcode.LDI, R3, ANY, 1),
                new Word(Opcode.STD, R4, ANY, 21), // 51
                new Word(Opcode.LDI, R1, ANY, 22), // 52
                new Word(Opcode.LDI, R7, ANY, 6), 
                new Word(Opcode.LDI, R8, ANY, 31), // 61
                new Word(Opcode.LDI, R4, ANY, 0), 
                new Word(Opcode.ADD, R4, R2, -1),
                new Word(Opcode.LDI, R2, ANY, 0), 
                new Word(Opcode.ADD, R2, R3, -1),
                new Word(Opcode.ADD, R3, R4, -1), 
                new Word(Opcode.STX, R1, R3, -1),
                new Word(Opcode.ADDI, R1, ANY, 1), 
                new Word(Opcode.SUB, R8, R1, -1),
                new Word(Opcode.JMPIG, R7, R8, -1), 
                new Word(Opcode.STOP, ANY, ANY, -1) 
        };

        public Word[] fibonacci = new Word[]{
                // Se quiser ver o fibonacci ate outro valor, altere o K na primeira linha (no caso o 10),  memoria de saida comeca no 50 e termina no 99
                new Word(Opcode.LDI,R2,ANY,10), // poe valor no r1														r1 =5
                new Word(Opcode.STD, R2,ANY,50), // memoria[50] = r1
                new Word(Opcode.LDD, R8,ANY,50), // r7 = memoria[50] ---- r7 vai ser o contador
                new Word(Opcode.LDI,R1,ANY,52), // r0=52
                new Word(Opcode.LDI, R5,ANY, -1), // r4=-1
                new Word(Opcode.LDI,R3,ANY,27), // poe valor de pc para por o r1 na memoria de saida no r2			r2 = posicao de saida
                new Word(Opcode.JMPIL, R3,R2,-1), // se r1<0 pula pra pc de r2, se nao continua						se r1<0, vai para a posicao de r2
                //se >0
                new Word(Opcode.LDI, R2, ANY, 0),   // r1= 0
                new Word(Opcode.LDI, R3, ANY, 1),   // r2= 1
                new Word(Opcode.LDI, R7, ANY,28), // r6=pc do final
                new Word(Opcode.STD, R2,ANY,50), // memoria[60] = r1
                new Word(Opcode.SUBI, R8, ANY, 1),  // r7=r7-1
                new Word(Opcode.JMPIE, R7, R8, -1), // se r7==0 pc = r6
                new Word(Opcode.STD, R3,ANY,51), // memoria[61] = r2
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

                new Word(Opcode.STD, R5,ANY,50), // armazena na memoria 60 o valor do r4, no caso -1					MEMORIA[30] = r1

                new Word(Opcode.STOP,ANY,ANY,-1)
        };
        
        public Word[] fatorial = new Word[]{
                new Word(Opcode.LDI,R1,ANY,5), // poe valor no r1												1 		r1 =5
                new Word(Opcode.STD, R1,ANY,29), // memoria[29] = r1                                           2
                new Word(Opcode.LDD, R1,ANY,29), // r1 = memoria[29]                                          3
                new Word(Opcode.LDI,R2,ANY,18), // poe valor de pc para por o r1 na memoria de saida no r2	4		r2 = posicao de saida

                new Word(Opcode.JMPIL, R2,1,-1), // se r1<0 pula pra pc de r2, se nao continua						se r1<0, vai para a posicao de r2

                new Word(Opcode.STD, R1,ANY,30), // coloca o q ta no r1 na memoria 30									memoria[30] = r1
                new Word(Opcode.LDI,R5,ANY,30),  //poe em r5 a posicao da memoria										r5 = 30
                new Word(Opcode.LDX,R2,R5,-1), // r2 = m[30]															r2 = memoria[r5]
                new Word(Opcode.SUBI,R2,ANY,1), //r2=r2-1																r2 = r2-1 => fatorial(5 *(5-1))
                new Word(Opcode.LDI, R4,ANY,10), // coloca no r4 o pc do inicio da multiplicacao						r4 = a posicao onde comeca o multiplicador
                // multiplica
                new Word(Opcode.MULT,R1,R2,-1), // multiplica r1,r2													r1= r*2
                new Word(Opcode.SUBI,R2,ANY,1), // r2=r2-1															r2--
                new Word(Opcode.JMPIG,R4,R2,-1), // se r2>0 entao pula pra r4, no inicio da multi						if(r2>0) go to r4 (MULT)

                new Word(Opcode.LDI, R4,ANY,18), // coloca no r4 o pc do fim											r4 = posicao do final
                new Word(Opcode.STD,R1,ANY,23), // coloca o q ta em r1 em 30											MEMORIA[30] = r1
                new Word(Opcode.JMPIE,R4,R3,-1), // se r3==0 entao pula pra r4, no inicio da multi   					r3 =0 , if(r3==0) pula pro final (r4)
                new Word(Opcode.LDI,R1,ANY,-1), // coloca -1 em r1
                //final
                new Word(Opcode.STD, R1,ANY,23), // armazena na memoria 0 o valor do r1, no caso -1					MEMORIA[30] = r1
                new Word(Opcode.STOP, ANY,ANY,-1)
        };

        public Word[] bubbleSort = new Word[] {
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
                new Word(Opcode.STOP, ANY, ANY, -1)
        };


        public Word[] systemCallTest = new Word[]{
                new Word(Opcode.LDI, R8, ANY, 1),
                new Word(Opcode.TRAP, R8, R9, -1),
                new Word(Opcode.LDI, R8, ANY, 2),
                new Word(Opcode.TRAP, R8, R9, -1)
        };

        public Word[] systemInterruptionTest = new Word[]{
                new Word(Opcode.LDD, R1, ANY, 2000),
                new Word(Opcode.LDD, R1, ANY, -5),
                new Word(Opcode.STOP, ANY, ANY, -1),
        };

}
