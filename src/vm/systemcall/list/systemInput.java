package vm.systemcall.list;

import vm.CPU;
import vm.concurrency.SystemInHelper;
import vm.systemcall.SystemCall;

import java.util.Scanner;

public class systemInput implements SystemCall {

    private final SystemInHelper systemInHelper;

    public systemInput() {
        this.systemInHelper = SystemInHelper.getInstance();
    }

    @Override
    public boolean shouldExecuteCall(int registryValue) {
        return registryValue == 1;
    }

    @Override
    public void executeCall(CPU cpu) {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Insira um numero para preencher o registrador: ");
        cpu.getRegistries()[8] = in.nextInt();
        in.close();
    }

}
