package vm.concurrency;

import java.util.Scanner;

public class SystemInHelper {

    private static SystemInHelper INSTANCE;
    private final Scanner scanner;

    private SystemInHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public static SystemInHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SystemInHelper(new Scanner(System.in));
        }
        return INSTANCE;
    }

    public boolean hasNext(String pattern) {
        return scanner.hasNext(pattern);
    }

    //Talvez fazer com que isso seja um semaforo, dar prioriedade para o console. Shell tenta da aquired
    public synchronized String read() {
        return scanner.next();
    }
}
