package vm;// PUCRS - Escola Politécnica - Sistemas Operacionais

import vm.memory.PCB;
import vm.programs.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/*
@TODO
- Rodar um dump mais esperto, ao final do STOP rodar o dump do programa executado

- Quem sabe fazer um consolezinho pro cara carregar os programas deles em memoria, ser mais iterativo
    - Se tiver console, informar quando quer dump na tela ou então em um arquivo.
- Carregar programas para a memoria lendo de um arquivo
 */
public class Computer {
    public SystemOperational systemOperational;

    public Computer() {
        systemOperational = new SystemOperational();
    }

    public static void main(String args[]) {
        List<Program> programs = Arrays.asList(new Fibo(), new Factorial(), new BubbleSort(), new SystemCallTest(), new InterruptTest());
        //List<Program> programs = Arrays.asList(new Fibo());
        Computer computer = new Computer();
        programs.forEach(it -> {
            Optional<PCB> pcb = computer.systemOperational.loadProgram(it);
            if (pcb.isEmpty()) {
                System.err.println("### Could not load program: " + it.getClass().getName() + " due less memory then required");
            }
        });
        computer.systemOperational.start();
    }
}

