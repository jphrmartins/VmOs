package vm.systemcall;


import vm.CPU;
/**
 * SystemCall
 */
public interface SystemCall {
    boolean shouldExecuteCall(int registryValue);
    void executeCall(CPU cpu);
    
}