package vm.systemcall;


import vm.CPU;
/**
 * SystemCall
 */
public interface SystemCall {
    void executeCall(CPU cpu);
    
}