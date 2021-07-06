package vm.interruptions.list;

import vm.OperationalSystem;
import vm.interruptions.SystemInterrupt;
import vm.memory.PCB;

public class StopInterruption implements SystemInterrupt {
    @Override
    public boolean handleInterrupt(OperationalSystem operationalSystem) {
        PCB currentPCB = operationalSystem.getCpu().getCurrentPCB();
        System.out.println("STOP code was called by Program: " + currentPCB.getProgramName()
                + ", Will remove program from memory");
        operationalSystem.unloadProgram(currentPCB);
        return true;
    }
}
