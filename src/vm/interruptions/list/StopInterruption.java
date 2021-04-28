package vm.interruptions.list;

import vm.SystemOperational;
import vm.interruptions.SystemInterrupt;
import vm.memory.PCB;

public class StopInterruption implements SystemInterrupt {
    @Override
    public boolean handleInterrupt(SystemOperational systemOperational) {
        System.out.println("STOP code was called, Will remove program from memory");
        PCB currentPCB = systemOperational.cpu.getCurrentPCB();
        systemOperational.readyList.remove(currentPCB);
        systemOperational.unloadProgram(currentPCB);
        return true;
    }
}
