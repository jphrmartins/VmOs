package vm.systemcall.list;

import vm.CPU;
import vm.systemcall.SystemCall;

import java.util.Scanner;

public class systemInput implements SystemCall {

    @Override
    public void executeCall(CPU cpu) {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Insira um numero para preencher o registrador: ");
        cpu.getRegistries()[8] = in.nextInt();
        in.close();
    }

}
