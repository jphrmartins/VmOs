package vm.interruptions.list;

import vm.interruptions.SystemInterrupt;

public class InvalidTrapInterruption implements SystemInterrupt {
    private final int callCommand;

    public InvalidTrapInterruption(int command){
        callCommand = command;
    }
    @Override
    public String getReason() {
        return "Invalid System call command: " + callCommand;
    }

    @Override
    public boolean shouldHalt() {
        return true;
    }
    
}
