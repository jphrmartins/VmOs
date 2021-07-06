package vm.concurrency;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class SystemInHelper {

    private static SystemInHelper INSTANCE;
    private final Scanner scanner;
    private boolean threadWaiting;

    private SystemInHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public static SystemInHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SystemInHelper(new Scanner(System.in));
        }
        return INSTANCE;
    }

    public void acquire() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is requesting terminal, press any key and enter to pass the control.");
        threadWaiting = true;
    }

    public void release() {
        threadWaiting = false;
    }

    public boolean hasNext() {
        if (threadWaiting) {
            return false;
        }
        return scanner.hasNextLine();
    }

    public synchronized String read(String previusMessage) {
        System.out.print(previusMessage);
        return scanner.next();
    }
}
