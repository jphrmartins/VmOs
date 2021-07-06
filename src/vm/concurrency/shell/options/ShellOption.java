package vm.concurrency.shell.options;

import vm.OperationalSystem;
import vm.programs.Program;

import java.util.List;

public interface ShellOption {
    boolean isRightOption(int option);
    void execOption(OperationalSystem operationalSystem, List<Program> programList);
}
