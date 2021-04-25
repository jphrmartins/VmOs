package vm.memory;

public class CPUState {
    private final int lastProgramCounter;
    private final int[] registers;

    public CPUState(int lastProgramCounter, int[] registers) {
        this.lastProgramCounter = lastProgramCounter;
        this.registers = registers;
    }

    public int getLastProgramCounter() {
        return lastProgramCounter;
    }

    public int[] getRegisters() {
        return registers;
    }
}
