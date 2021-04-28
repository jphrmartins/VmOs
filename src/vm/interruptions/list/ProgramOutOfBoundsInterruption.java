package vm.interruptions.list;

import vm.interruptions.HaltInterruption;
import vm.memory.PCB;

public class ProgramOutOfBoundsInterruption extends HaltInterruption {
    public ProgramOutOfBoundsInterruption(PCB currentPCB, int registerValue) {
        super("Memory Position: " + registerValue + " is not in program: " + currentPCB.getClass().getName() + " range");
    }
}
