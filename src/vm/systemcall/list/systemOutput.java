package vm.systemcall.list;

import vm.CPU;
import vm.systemcall.SystemCall;

public class systemOutput implements SystemCall{

    @Override
    public void executeCall(CPU cpu) {
        System.out.println("R9 content: " + cpu.getRegistries()[8]);
    }
    
}
