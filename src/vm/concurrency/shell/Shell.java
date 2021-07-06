package vm.concurrency.shell;

import vm.OperationalSystem;
import vm.concurrency.SystemInHelper;
import vm.memory.PCB;
import vm.programs.Program;

import java.util.List;
import java.util.Optional;

public class Shell extends Thread {
    private final String programInputPattern;
    private final SystemInHelper systemInHelper;
    private final OperationalSystem operationalSystem;
    private final List<Program> programList;

    public Shell(OperationalSystem operationalSystem, List<Program> programList) {
        super("Shell Thread");
        this.programInputPattern = createProgramInputPattern(programList.size());
        this.systemInHelper = SystemInHelper.getInstance();
        this.operationalSystem = operationalSystem;
        this.programList = programList;
    }

    @Override
    public void run() {
        showProgramList();
        while (true) {
            if (systemInHelper.hasNext()) {
                System.out.println("Shell has input to read");
                String token = systemInHelper.read("");
                if (token.matches(programInputPattern)) {
                    System.out.println("Shell input is valid");
                    int option = Integer.parseInt(token.split("#")[1]);
                    if (option == 0) {
                        showProgramList();
                    } else if (option == -1) {
                        operationalSystem.dumpToFile();
                    } else if (option > 0 && option < programList.size()) {
                        Program program = programList.get(option - 1);
                        System.out.println("Program " + program.getName() + " is Loading");
                        Optional<PCB> loadedProgram = this.operationalSystem.loadProgram(program);
                        if (loadedProgram.isPresent()) {
                            System.out.println("Program: " + program.getName() + " is Loaded in memory");
                        } else {
                            System.out.println("Could not load " + program.getName() + " due lack of memory");
                        }
                    }
                }
            }
        }
    }

    private void showProgramList() {
        System.out.println("Escrava # + código para executar o comando desejado -> ex: #1");
        System.out.println("- #-3 para ver a lista a ser rodado");
        System.out.println("- #-2 para começar a execuçaõ");
        System.out.println("- #-1 para ver o dump");
        System.out.println("- #0 para ver a lista novamente");
        System.out.println("Lista de programas disponíveis: ");
        for (int i = 1; i <= programList.size(); i++) {
            System.out.println(i + ". Nome: " + programList.get(i - 1).getName());
        }
    }

    private String createProgramInputPattern(int programListSize) {
        StringBuilder pattern = new StringBuilder("^#(-3|-2|-1|0|");
        for (int i = 1; i <= programListSize; i++) {
            if (i == programListSize) {
                pattern.append(i).append(")$");
            } else {
                pattern.append(i).append("|");
            }
        }
        return pattern.toString();
    }
}
