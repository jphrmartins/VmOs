package vm.concurrency.shell.options;

import vm.OperationalSystem;
import vm.programs.Program;

import java.util.List;

public class CommandList implements ShellOption {
    @Override
    public boolean isRightOption(int option) {
        return option == 0;
    }

    @Override
    public void execOption(OperationalSystem operationalSystem, List<Program> programList) {

    }
}
