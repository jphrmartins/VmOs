package vm.interruptions.list;

import vm.OperationalSystem;
import vm.interruptions.SystemInterrupt;
import vm.memory.PCB;

public class ConsoleRequestResponseInterruption implements SystemInterrupt {
    private PCB programRequestedInterrupt;

    public ConsoleRequestResponseInterruption(PCB programRequestedInterrupt) {
        this.programRequestedInterrupt = programRequestedInterrupt;
    }

    @Override
    public boolean handleInterrupt(OperationalSystem operationalSystem) {
        this.programRequestedInterrupt.setBlocked(false);
        operationalSystem.addToReadyList(this.programRequestedInterrupt);
        return false;
    }
}
