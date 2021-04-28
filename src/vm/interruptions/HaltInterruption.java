package vm.interruptions;

import vm.SystemOperational;

public abstract class HaltInterruption implements SystemInterrupt {
    private final String reason;

    public HaltInterruption(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean handleInterrupt(SystemOperational systemOperational) {
        System.out.print(this.getClass().getName() + " happen due to " + reason);
        System.out.println(" Halting System");
        return true;
    }
}
