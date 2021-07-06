package vm.systemcall;


import vm.CPU;
import vm.memory.CPUState;

/**
 * SystemCall
 */
public interface SystemCall {
    boolean shouldExecuteCall(int registryValue);
    void executeCall(CPUState cpu);
    
}