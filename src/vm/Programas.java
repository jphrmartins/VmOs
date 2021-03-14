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

        public Word[] bubbleSort = new Word[] {
                new Word(Opcode.LDI, R1, ANY, 300),
                new Word(Opcode.LDI, R2, ANY, 500),
                new Word(Opcode.SWAP, R1, R2, -1),
                new Word(Opcode.STD, R1, ANY, 7),
                new Word(Opcode.STD, R2, ANY, 8),
                new Word(Opcode.STOP, ANY, ANY, -1)
        };

}
