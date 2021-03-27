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
		//s.bubbleSortProgram();
		s.systemcallTest();
	}

	public void test1(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().fibonacci10;
		memoryHelper.carga(p, systemOperational.m);
		systemOperational.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(systemOperational.m, 0, 33);
		System.out.println("---------------------------------- após execucao ");
		systemOperational.cpu.run();
		memoryHelper.dump(systemOperational.m, 0, 33);
	}

	public void test2(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().progMinimo;
		memoryHelper.carga(p, systemOperational.m);
		systemOperational.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(systemOperational.m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		systemOperational.cpu.run();
		memoryHelper.dump(systemOperational.m, 0, 15);
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

}

