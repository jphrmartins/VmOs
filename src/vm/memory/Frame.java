package vm.memory;

public class Frame {
    private static Integer FRAMES_ID = 0;
    private int frameId;
    private boolean allocated;
    private int startIndex;
    private int lastIndex;

    public Frame(int startIndex, int lastIndex) {
        this.frameId = FRAMES_ID++;
        this.startIndex = startIndex;
        this.lastIndex = lastIndex;
    }

    public boolean isAllocated() {
        return allocated;
    }

    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }

    public int getFrameId() {
        return frameId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getLastIndex() {
        return lastIndex;
    }
}
