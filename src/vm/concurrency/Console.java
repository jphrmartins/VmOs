package vm.concurrency;

import vm.SystemOperational;
import vm.memory.PCB;
import vm.systemcall.SystemCall;

import java.util.List;

public class Console extends Thread {

    private List<SystemCall> systemCallers;
    private SystemOperational systemOperational;



    @Override
    public void run() {
        while (true) {
            if (!systemOperational.getConsoleRequestList().isEmpty()) {
                PCB request = systemOperational.getConsoleRequestList().remove(0);
                synchronized (this) {
                    handleConsoleRequest(request);
                }
                systemOperational.interruptCpu();
            }
        }
    }

    private void handleConsoleRequest(PCB request) {
        int[] registries = request.getCpuState().getRegisters();
        int option = registries[7];

    }
}
