package vm.systemcall;

/**
 * SystemCall
 */
public interface SystemCall {
    boolean shouldCall();
    int call();
    
}