package vm;// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Fase 1 - máquina virtual (vide enunciado correspondente)
//

public class Computer {
	public SystemOperational systemOperational;
    public Computer(){   // a vm.VM com tratamento de interrupções
		 systemOperational = new SystemOperational();
	}
	public static void main(String args[]) {
		Computer s = new Computer();
		//s.fibonacci();
		//s.fatorial();
		//s.bubbleSortProgram();
		//s.systemcallTest();
		s.systemInterruptTest();
		
	}

	public void fibonacci(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().fibonacci;
		memoryHelper.carga(p, systemOperational.m);
		systemOperational.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(systemOperational.m, 0, 60);
		System.out.println("---------------------------------- após execucao ");
		systemOperational.cpu.run();
		memoryHelper.dump(systemOperational.m, 0, 60);
	}

	public void fatorial(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().fatorial;
		memoryHelper.carga(p, systemOperational.m);
		systemOperational.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(systemOperational.m, 0, 24);
		System.out.println("---------------------------------- após execucao ");
		systemOperational.cpu.run();
		memoryHelper.dump(systemOperational.m, 0, 24);
	}

	public void bubbleSortProgram(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().bubbleSort;
		memoryHelper.carga(p, systemOperational.m);
		systemOperational.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(systemOperational.m, 0, 65);
		System.out.println("---------------------------------- após execucao ");
		systemOperational.cpu.run();
		memoryHelper.dump(systemOperational.m, 0, 65);
	}

	public void systemcallTest(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().systemCallTest;
		memoryHelper.carga(p, systemOperational.m);
		systemOperational.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(systemOperational.m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		systemOperational.cpu.run();
		memoryHelper.dump(systemOperational.m, 0, 15);
	}

	public void systemInterruptTest(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().systemInterruptionTest;
		memoryHelper.carga(p, systemOperational.m);
		systemOperational.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(systemOperational.m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		systemOperational.cpu.run();
		memoryHelper.dump(systemOperational.m, 0, 15);
	}

}

