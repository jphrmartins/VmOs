package vm.interruptions;

import vm.OperationalSystem;

public interface SystemInterrupt {
    boolean handleInterrupt(OperationalSystem operationalSystem);
}
