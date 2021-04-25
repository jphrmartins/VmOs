package vm.interruptions;

import vm.SystemOperational;

public interface SystemInterrupt {
    boolean handleInterrupt(SystemOperational systemOperational);
}
