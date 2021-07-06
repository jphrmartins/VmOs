package vm.concurrency;

import vm.OperationalSystem;
import vm.memory.PCB;
import vm.systemcall.SystemCall;

import java.util.List;

public class Console extends Thread {

    private final List<SystemCall> systemCallers;
    private final OperationalSystem operationalSystem;

    public Console(List<SystemCall> systemCallers, OperationalSystem operationalSystem) {
        super("Console Thread");
        this.systemCallers = systemCallers;
        this.operationalSystem = operationalSystem;
    }

    @Override
    public void run() {
        while (true) {
            if (!operationalSystem.getConsoleRequestList().isEmpty()) {
                PCB request = operationalSystem.getConsoleRequestList().remove(0);
                handleConsoleRequest(request);
                operationalSystem.onIoRequestResponse(request);
            }
        }
    }

    private void handleConsoleRequest(PCB request) {
        int[] registries = request.getCpuState().getRegisters();
        int option = registries[7];
        for (SystemCall systemCall: systemCallers) {
            if (systemCall.shouldExecuteCall(option)) {
                systemCall.executeCall(request.getCpuState());
                break;
            }
        }
    }
}
