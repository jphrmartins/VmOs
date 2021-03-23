package vm.interruptions;

public interface SystemInterrupt {
    String getReason();
    boolean shouldHalt();
}
