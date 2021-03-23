package vm.interruptions;

import vm.CPU;
import vm.interruptions.SystemInterrupt;

public class InterruptionHandler {
    public boolean handleInterruption(CPU cpu, SystemInterrupt interrupt) {
        System.out.print(interrupt.getClass().getName() + " happen due to ");
        System.out.println(interrupt.getReason());
        System.out.println("System must halt? " + (interrupt.shouldHalt() ? "YES" : "NO"));
        return interrupt.shouldHalt();
    }


}
