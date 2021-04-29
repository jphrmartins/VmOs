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

    //@TODO - O PAGE NÃO É O VALOR NA MEMORIA O ANIMAL... É O INDICE DO ALLOCATED.. :NUKE-PALM:
    //ass: jp, código feito por: jp, um animal...
    public Optional<Integer> getMemoryPosition(int programMemoryPointer) {
        int page = programMemoryPointer / pageSize;
        if (page >= allocatedFrames.length) return Optional.empty();
        int offset = programMemoryPointer % pageSize;
        return Optional.of(allocatedFrames[page] * pageSize + offset);
    }

    public void saveState(CPU cpu) {
        int[] registers = Arrays.copyOf(cpu.getRegistries(), cpu.getRegistries().length);
        cpuState = new CPUState(cpu.getProgramCounter(), registers);
    }

    public CPUState getCpuState() {
        return cpuState;
    }

    public int getCurrentProgramCounter() {
        return cpuState != null
                ? cpuState.getLastProgramCounter()
                : 0;
    }
}
