package vm.interruptions.list;

import vm.SystemOperational;
import vm.interruptions.SystemInterrupt;

public abstract class HaltInterruption implements SystemInterrupt {
    private String reason;

    public HaltInterruption(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean handleInterrupt(SystemOperational systemOperational) {
        System.out.print(this.getClass().getName() + " happen due to " + reason);
        System.out.println("Halting System");
        return true;
    }
}
