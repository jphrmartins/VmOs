package vm.systemcall;

import vm.CPU;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.InvalidTrapInterruption;
import vm.systemcall.list.systemInput;
import vm.systemcall.list.systemOutput;

public class SystemCallHandler {
    public SystemInterrupt call(CPU cpu){
        int reg8Content = cpu.getRegistries()[7];
        
        switch (reg8Content) {
            case 1:
                new systemInput().executeCall(cpu);
                break;
        
            case 2:
                new systemOutput().executeCall(cpu);
                break;
            default:
                return new InvalidTrapInterruption(reg8Content);
        }
        return null;
    }
}
