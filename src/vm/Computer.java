package vm;// PUCRS - Escola Politécnica - Sistemas Operacionais

import vm.programs.*;

import java.util.Arrays;
import java.util.List;

/*
@TODO
- Rodar um dump mais esperto, ao final do STOP rodar o dump do programa executado

- Quem sabe fazer um consolezinho pro cara carregar os programas deles em memoria, ser mais iterativo
    - Se tiver console, informar quando quer dump na tela ou então em um arquivo.
- Carregar programas para a memoria lendo de um arquivo
 */
public class Computer {
    public OperationalSystem operationalSystem;

    public Computer(List<Program> programs) throws InterruptedException {
        operationalSystem = new OperationalSystem(programs);
    }

    public static void main(String args[]) throws InterruptedException {
        List<Program> programs = Arrays.asList(new ProgMinimo(), new Fibo(), new Factorial(), new BubbleSort(), new SystemCallTest(), new InterruptTest());
        //List<Program> programs = Arrays.asList(new Fibo());
        Computer computer = new Computer(programs);
        computer.operationalSystem.start();
    }
}

