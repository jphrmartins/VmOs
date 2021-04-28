package vm.interruptions.list;

import vm.SystemOperational;
import vm.interruptions.SystemInterrupt;
import vm.memory.PCB;

import java.util.Optional;

public class StopInterruption implements SystemInterrupt {
    @Override
    public boolean handleInterrupt(SystemOperational systemOperational) {
        System.out.println("STOP code was called, Will remove program from memory");
        PCB currentPCB = systemOperational.cpu.getCurrentPCB();
        Optional<PCB> systemProgramPCB = systemOperational.readyList.stream()
                .filter(pcb -> pcb.getPid() == currentPCB.getPid())
                .findFirst();
        systemProgramPCB.ifPresent(systemOperational.readyList::remove);
        systemOperational.unloadProgram(currentPCB);
        return true;
    }
}
