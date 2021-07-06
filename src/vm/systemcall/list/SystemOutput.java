package vm.systemcall.list;

import vm.memory.CPUState;
import vm.systemcall.SystemCall;

public class SystemOutput implements SystemCall{

    @Override
    public boolean shouldExecuteCall(int registryValue) {
        return registryValue == 2;
    }

    @Override
    public void executeCall(CPUState cpu) {
        System.out.println("R9 content: " + cpu.getRegisters()[8]);
    }
    
}
