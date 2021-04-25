package vm.interruptions;

import vm.memory.PCB;

public class ProgramOutOfBoundsInterruption implements SystemInterrupt {
    private final String programName;
    private final int invalidPostion;
    public ProgramOutOfBoundsInterruption(PCB currentPCB, int registerValue) {
        programName = currentPCB.getClass().getName();
        invalidPostion = registerValue;
    }

    @Override
    public String getReason() {
        return "Memory Position: " + invalidPostion + " is not in program: " + programName + " range";
    }

    @Override
    public boolean shouldHalt() {
        return true;
    }
}
