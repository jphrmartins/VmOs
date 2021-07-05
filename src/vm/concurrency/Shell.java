package vm.concurrency;

import vm.SystemOperational;
import vm.memory.PCB;
import vm.programs.Program;

import java.util.List;
import java.util.Optional;

public class Shell extends Thread {
    private final String programInputPattern;
    private final SystemInHelper systemInHelper;
    private final SystemOperational systemOperational;
    private final List<Program> programList;

    public Shell(SystemOperational systemOperational, List<Program> programList) {
        super("Shell Thread");
        this.programInputPattern = createProgramInputPattern(programList.size());
        this.systemInHelper = SystemInHelper.getInstance();
        this.systemOperational = systemOperational;
        this.programList = programList;
    }

    @Override
    public void run() {
        showProgramList();
        while (true) {
            if (systemInHelper.hasNext(programInputPattern)) {
                int option = Integer.parseInt(systemInHelper.read().split("#")[1]);
                if (option == 0) {
                    showProgramList();
                } else {
                    Program program = programList.get(option);
                    System.out.println("Program " + program.getName() + " is Loading");
                    Optional<PCB> loadedProgram =  this.systemOperational.loadProgram(program);
                    if (loadedProgram.isPresent()) {
                        System.out.println("Program: " + program.getName() + " is Loaded in memory");
                    } else {
                        System.out.println("Could not load " + program.getName() + " due lack of memory");
                    }
                }
            }
        }
    }

    private void showProgramList() {
        System.out.println("Escrava # + código do programa para rodar o programa desejado, ex: #1");
        System.out.println("Ou então #0 para ver a lista novamente");
        System.out.println("Lista de programas disponíveis: ");
        for (int i = 1; i <= programList.size(); i++) {
            System.out.println(i + ". Nome: " + programList.get(i).getName());
        }
    }

    private String createProgramInputPattern(int programListSize) {
        String pattern = "^#(";
        for (int i = 1; i <= programListSize ; i++) {
            if (i == programListSize) {
                pattern += i + ")$";
            } else {
                pattern =+ i + "|";
            }
        }
        return pattern;
    }
}
