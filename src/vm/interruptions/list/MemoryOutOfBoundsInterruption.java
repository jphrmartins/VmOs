package vm.interruptions.list;

import vm.interruptions.HaltInterruption;

public class MemoryOutOfBoundsInterruption extends HaltInterruption {
    public MemoryOutOfBoundsInterruption(int programCounter, int memorySize) {
        super("Memory has " + memorySize + " positions, and counter tried to get position: " + programCounter);
    }
}
