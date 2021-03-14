package vm;// PUCRS - Escola Politécnica - Sistemas Operacionais
// Prof. Fernando Dotti
// Código fornecido como parte da solução do projeto de Sistemas Operacionais
//
// Fase 1 - máquina virtual (vide enunciado correspondente)
//

public class Sistema {
	public VM vm;
    public Sistema(){   // a vm.VM com tratamento de interrupções
		 vm = new VM();
	}
	public static void main(String args[]) {
		Sistema s = new Sistema();
//		s.test2();
//		s.test1();
		s.test3();
	}

	public void test1(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().fibonacci10;
		memoryHelper.carga(p, vm.m);
		vm.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(vm.m, 0, 33);
		System.out.println("---------------------------------- após execucao ");
		vm.cpu.run();
		memoryHelper.dump(vm.m, 0, 33);
	}

	public void test2(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().progMinimo;
		memoryHelper.carga(p, vm.m);
		vm.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(vm.m, 0, 15);
		System.out.println("---------------------------------- após execucao ");
		vm.cpu.run();
		memoryHelper.dump(vm.m, 0, 15);
	}

	public void test3(){
		MemoryHelper memoryHelper = new MemoryHelper();
		Word[] p = new Programas().bubbleSort;
		memoryHelper.carga(p, vm.m);
		vm.cpu.setContext(0);
		System.out.println("---------------------------------- programa carregado ");
		memoryHelper.dump(vm.m, 0, 9);
		System.out.println("---------------------------------- após execucao ");
		vm.cpu.run();
		memoryHelper.dump(vm.m, 0, 9);
	}

}

