package vm;// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Fase 1 - máquina virtual (vide enunciado correspondente)
//

import vm.memory.PCB;
import vm.programs.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Computer {
    public SystemOperational systemOperational;

    public Computer() {   // a vm.VM com tratamento de interrupções
        systemOperational = new SystemOperational();
    }

    public static void main(String args[]) {
        List<Program> programs = Arrays.asList(new Fibo(), new Factorial(),
                new BubbleSort(), new SystemCallTest(), new InterruptTest());
        Computer computer = new Computer();
        programs.forEach(it -> {
            Optional<PCB> pcb = computer.systemOperational.loadProgram(it);
            if (pcb.isEmpty()) {
                System.err.println("### Could not load program: " + it.getClass().getName() + " due less memory then required");
            }
        });
        computer.systemOperational.start();
        //s.fibonacci();
        //s.fatorial();
        //s.bubbleSortProgram();
        //s.systemcallTest();
    }

//	public void fibonacci(){
//		MemoryHelper memoryHelper = new MemoryHelper();
//		Word[] p = new Programas().fibonacci;
//		memoryHelper.carga(p, systemOperational.m);
//		systemOperational.cpu.setContext(0);
//		System.out.println("---------------------------------- programa carregado ");
//		memoryHelper.dump(systemOperational.m, 0, 60);
//		System.out.println("---------------------------------- após execucao ");
//		systemOperational.cpu.run();
//		memoryHelper.dump(systemOperational.m, 0, 60);
//	}

}

