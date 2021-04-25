package vm.memory;

import vm.CPU;

import java.util.Arrays;
import java.util.Optional;

public class PCB {
    private static Integer PCB_GLOBAL_PID = 0;
    private final int pid;
    private final Integer pageSize;
    private CPUState cpuState;
    private final int[] allocatedFrames;

    public PCB(int[] allocatedFrames, int pageSize) {
        pid = PCB_GLOBAL_PID++;
        this.pageSize = pageSize;
        this.allocatedFrames = allocatedFrames;
    }

    public int getPid() {
        return pid;
    }

    public int[] getAllocatedFrames() {
        return allocatedFrames;
    }

    public Optional<Integer> getMemoryPosition(int programMemoryPointer) {
        int page = programMemoryPointer / pageSize;
        if (!inPageRange(page)) return Optional.empty();
        int offset = programMemoryPointer % pageSize;
        return Optional.of(allocatedFrames[page] * pageSize + offset);
    }

    private boolean inPageRange(int page) {
        return Arrays.stream(allocatedFrames).anyMatch(it -> it == page);
    }

    public void saveState(CPU cpu) {
        cpuState = new CPUState(cpu.getProgramCounter(), cpu.getRegistries());
    }

    public CPUState getCpuState() {
        return cpuState;
    }

    public int getCurrentProgramCounter() {
        return cpuState != null
                ? cpuState.getLastProgramCounter()
                : allocatedFrames[0] * pageSize;
    }
}
