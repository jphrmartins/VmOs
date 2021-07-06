package vm.memory;

import vm.CPU;

import java.util.Arrays;
import java.util.Optional;

public class PCB {
    private static Integer PCB_GLOBAL_PID = 0;
    private final int pid;
    private final String programName;
    private final Integer pageSize;
    private boolean blocked;
    private CPUState cpuState;
    private final int[] allocatedFrames;

    public PCB(String programName, int[] allocatedFrames, int pageSize) {
        this.pid = PCB_GLOBAL_PID++;
        this.programName = programName;
        this.blocked = false;
        this.pageSize = pageSize;
        this.allocatedFrames = allocatedFrames;
    }

    public int[] getAllocatedFrames() {
        return allocatedFrames;
    }

    public Optional<Integer> getMemoryPosition(int programMemoryPointer) {
        int page = programMemoryPointer / pageSize;
        if (page >= allocatedFrames.length) return Optional.empty();
        int offset = programMemoryPointer % pageSize;
        return Optional.of(allocatedFrames[page] * pageSize + offset);
    }

    public String getProgramName() {
        return programName;
    }

    public void saveState(CPU cpu) {
        int[] registers = Arrays.copyOf(cpu.getRegistries(), cpu.getRegistries().length);
        cpuState = new CPUState(cpu.getProgramCounter(), registers);
    }

    public int getPid() {
        return pid;
    }

    public CPUState getCpuState() {
        return cpuState;
    }

    public int getCurrentProgramCounter() {
        return cpuState != null
                ? cpuState.getLastProgramCounter()
                : 0;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked() {
        this.blocked = true;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
