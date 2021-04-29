package vm.interruptions.list;

import vm.SystemOperational;
import vm.interruptions.SystemInterrupt;
import vm.memory.PCB;

import java.util.Optional;

public class StopInterruption implements SystemInterrupt {
    @Override
    public boolean handleInterrupt(SystemOperational systemOperational) {
        PCB currentPCB = systemOperational.cpu.getCurrentPCB();
        System.out.println("STOP code was called by Program: " + currentPCB.getProgramName()
                + ", Will remove program from memory");
        systemOperational.unloadProgram(currentPCB);
        return true;
    }
}
