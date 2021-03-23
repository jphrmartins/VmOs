package vm.interruptions;

import vm.interruptions.SystemInterrupt;

public class InterruptionResult extends SystemInterrupt {
    private boolean shouldHalt;
    public InterruptionResult(String reason, boolean shouldHalt) {
        super(reason);
        this.shouldHalt = shouldHalt;
    }

    public boolean shouldHalt() {
        return shouldHalt;
    }
}
