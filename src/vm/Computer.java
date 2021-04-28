package vm;// PUCRS - Escola Politécnica - Sistemas Operacionais

import vm.memory.PCB;
import vm.programs.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
/*
@TODO
- Rodar um dump mais esperto, ao final do STOP rodar o dump do programa executado
- Dump final (da memoria toda) jogar para algum arquivo, para melhor visualização.

- Quem sabe fazer um consolezinho pro cara carregar os programas deles em memoria, ser mais iterativo
- Carregar programas para a memoria lendo de um arquivo
- Calcular espaço necessário para o espaço em memoria / usar um valor fixo :think:
 */
public class Computer {
    public SystemOperational systemOperational;

    public Computer() {   // a vm.VM com tratamento de interrupções
        systemOperational = new SystemOperational();
    }

    public static void main(String args[]) {
        List<Program> programs = Arrays.asList(new BubbleSort());
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

