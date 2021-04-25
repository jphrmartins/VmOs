package vm.interruptions.list;

import vm.SystemOperational;
import vm.interruptions.SystemInterrupt;

public class StopInterruption implements SystemInterrupt {
    @Override
    public String getReason() {
        return "STOP word called";
    }

    @Override
    public boolean shouldHalt() {
        return true;
    }

    @Override
    public boolean handleInterrupt(SystemOperational systemOperational) {
        return false;
    }
}
