package vm.interruptions.list;

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
}
