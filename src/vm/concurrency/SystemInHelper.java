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

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public synchronized String read(String previusMessage) {
        System.out.print(previusMessage);
        return scanner.next();
    }
}
