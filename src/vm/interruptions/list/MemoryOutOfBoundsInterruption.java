package vm.interruptions.list;

import vm.interruptions.SystemInterrupt;

public class MemoryOutOfBoundsInterruption implements SystemInterrupt {
    private final int programCounter;
    private final int memorySize;

    public MemoryOutOfBoundsInterruption(int programCounter, int memorySize) {
        this.programCounter = programCounter;
        this.memorySize = memorySize;
    }

    @Override
    public String getReason() {
        return "Memory has " + memorySize + " positions, and counter tried to get position: " + programCounter;
    }

    @Override
    public boolean shouldHalt() {
        return true;
    }
}
