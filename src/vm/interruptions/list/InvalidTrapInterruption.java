package vm.interruptions.list;

import vm.interruptions.HaltInterruption;
public class InvalidTrapInterruption extends HaltInterruption {
    public InvalidTrapInterruption(int command){
        super("Invalid System call command: " + command);
    }
    
}
