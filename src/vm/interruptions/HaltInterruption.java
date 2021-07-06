package vm.interruptions;

import vm.OperationalSystem;

public abstract class HaltInterruption implements SystemInterrupt {
    private final String reason;

    public HaltInterruption(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean handleInterrupt(OperationalSystem operationalSystem) {
        System.out.print(this.getClass().getName() + " happen due to " + reason);
        System.out.println(" Halting Current Program ");
        return true;
    }
}
