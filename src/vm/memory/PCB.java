package vm.memory;

import java.util.Optional;

public class PCB {
    private static Integer GLOBAL_PID = 0;
    private final Integer pid;
    private Integer pageSize;
    private final int[] allocatedFrames;

    public PCB(int[] allocatedFrames, int pageSize) {
        this.pid = GLOBAL_PID++;
        this.pageSize = pageSize;
        this.allocatedFrames = allocatedFrames;
    }

    public Integer getPid() {
        return pid;
    }

    public Optional<Integer> getMemoryPosition(int programMemoryPointer) {
        int page = programMemoryPointer / pageSize;
        if (page >= allocatedFrames.length) return Optional.empty();
        int offset = programMemoryPointer % pageSize;
        return Optional.of(allocatedFrames[page] * pageSize  + offset);
    }
}
