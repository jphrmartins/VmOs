package vm;

import vm.concurrency.Console;
import vm.concurrency.shell.Shell;
import vm.instructionhandle.*;
import vm.interruptions.SystemInterrupt;
import vm.interruptions.list.ConsoleRequestResponseInterruption;
import vm.memory.Frame;
import vm.memory.PCB;
import vm.programs.Program;
import vm.systemcall.SystemCall;
import vm.systemcall.list.SystemInput;
import vm.systemcall.list.SystemOutput;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

// ------------------- V M  - constituida de vm.CPU e MEMORIA -----------------------------------------------
// -------------------------- atributos e construcao da vm.VM -----------------------------------------------
public class OperationalSystem {
    private static final Integer FRAME_SIZE = 64;
    private final List<Frame> framesIndex;
    private final Integer wordsPerFrame;
    private final Console console;
    private final Shell shell;
    private boolean interrupted;
    private int tamMem;
    private Word[] memory;
    private final CPU cpu;
    private List<PCB> readyList;
    private List<PCB> consoleRequestList;

    public OperationalSystem(List<Program> programList) throws InterruptedException {
        tamMem = 1024;
        interrupted = false;
        wordsPerFrame = tamMem / FRAME_SIZE;
        readyList = new ArrayList<>();
        consoleRequestList = new ArrayList<>();
        memory = new Word[tamMem];
        for (int i = 0; i < tamMem; i++) {
            memory[i] = Word.emptyWord();
        }
        framesIndex = getFrames();
        Set<InstructionRule> instructionRules = new HashSet<>();
        Collections.addAll(instructionRules, new JMPRule(), new JMPIRule(), new JMPIGRule(), new JMPILRule(),
                new JMPIERule(), new JMPIMRule(), new JMPIGMRule(), new JMPILMRule(), new JMPIEMRule(),
                new ADDIRule(), new SUBIRule(), new ADDRule(), new SUBRule(), new MULTRule(), new LDIRule(),
                new LDDRule(), new STDRule(), new LDXRule(), new STXRule(), new SWAPRule(), new STOPRule(),
                new InvalidRule(), new TRAPRule()
        );
        console = new Console(getSystemIoCallers(), this);
        shell = new Shell(this, programList);
        cpu = new CPU(this, memory, instructionRules);
        cpu.start();
        console.start();
        shell.start();
    }

    public CPU getCpu() {
        return cpu;
    }

    public List<PCB> getConsoleRequestList() {
        return consoleRequestList;
    }

    public void addToReadyList(PCB pcb) {
        this.readyList.add(pcb);
    }

    public Optional<PCB> loadProgram(Program program) {
        Word[] programWords = program.createProgram(wordsPerFrame);
        int programWordIndex = 0;
        int pagesNeeds = (int) Math.ceil(programWords.length / (wordsPerFrame * 1.0));
        int[] allocatedFrames = new int[pagesNeeds];
        Arrays.fill(allocatedFrames, -1);
        int currentPage = 0;
        int availableFrames = getAvailableIndex();
        if (availableFrames < pagesNeeds) return Optional.empty();
        for (Frame frame : framesIndex) {
            if (currentPage >= allocatedFrames.length) break;
            if (!frame.isAllocated()) {
                for (int i = frame.getStartIndex(); i <= frame.getLastIndex(); i++) {
                    if (programWordIndex >= programWords.length) {
                        break;
                    }
                    memory[i] = programWords[programWordIndex];
                    programWordIndex++;
                }
                frame.setAllocated(true);
                allocatedFrames[currentPage++] = frame.getFrameId();
            }
        }
        PCB pcb = new PCB(program.getName(), allocatedFrames, wordsPerFrame);
        readyList.add(pcb);
        return Optional.of(pcb);
    }

    public boolean unloadProgram(PCB pcb) {
        int[] frames = pcb.getAllocatedFrames();
        for (int frame : frames) {
            framesIndex.get(frame).setAllocated(false);
        }
        return readyList.remove(pcb);
    }

    public void handleInterruption(SystemInterrupt interrupt) {
        this.interrupted = true;
        boolean shouldHalt = interrupt.handleInterrupt(this);
        if (shouldHalt) {
            cpu.cleanCurrentPCB();
        }
        this.interrupted = false;
    }

    public void handleProgramChange() {
        cpu.saveCurrentState();
        PCB pcb = cpu.getCurrentPCB();
        if (pcb.isBlocked()) {
            consoleRequestList.add(pcb);
        } else {
            readyList.add(pcb);
        }
        if (readyList.isEmpty()) {
            cpu.cleanCurrentPCB();
        } else {
            PCB nextPCB = readyList.remove(0);
            cpu.loadStateOf(nextPCB);
        }
    }

    //Main Thread
    public void start() throws InterruptedException {
        while (true) {
            System.out.println("Main thread is running");
            if (!readyList.isEmpty() && cpu.isIdle() && !this.isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " thread Will add program o cpu state");
                PCB pcb = readyList.remove(0);
                cpu.loadStateOf(pcb);
                synchronized (cpu) {
                    cpu.notify();
                }
            } else {
                synchronized (this) {
                    System.out.println("Noting to put on cpu or cpu is not idle, will wait");
                    wait();
                    System.out.println("There is something on readyList, will add on cpu");
                }
            }
        }
    }

    public void dumpToFile() {
        File file = new File("MemoryDump.txt");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < memory.length; i++) {
                printWriter.println(i + " - " + memory[i]);
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onIoRequestResponse(PCB programRequestedInterrupt) {
        this.handleInterruption(new ConsoleRequestResponseInterruption(programRequestedInterrupt));
    }

    private boolean isInterrupted() {
        return interrupted;
    }

    private int getAvailableIndex() {
        return (int) framesIndex.stream().filter(it -> !it.isAllocated()).count();
    }

    private List<Frame> getFrames() {
        List<Frame> frames = new ArrayList<>();
        for (int i = 0; i < memory.length; i = i + wordsPerFrame) {
            frames.add(new Frame(i, i + wordsPerFrame - 1));
        }
        return frames;
    }

    private List<SystemCall> getSystemIoCallers() {
        return Arrays.asList(new SystemInput(), new SystemOutput());
    }
}
