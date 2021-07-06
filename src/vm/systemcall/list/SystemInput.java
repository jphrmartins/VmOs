package vm.systemcall.list;

import vm.concurrency.SystemInHelper;
import vm.memory.CPUState;
import vm.systemcall.SystemCall;

public class SystemInput implements SystemCall {

    private final SystemInHelper systemInHelper;

    public SystemInput() {
        this.systemInHelper = SystemInHelper.getInstance();
    }

    @Override
    public boolean shouldExecuteCall(int registryValue) {
        return registryValue == 1;
    }

    @Override
    public void executeCall(CPUState cpu) {
        String textNumber = systemInHelper.read("Insira um numero para preencher o registrador: ");
        int number = Integer.parseInt(textNumber);
        cpu.getRegisters()[8] = number;
    }

}
