package vm;

import vm.instructionhandle.*;
import vm.interruptions.SystemInterrupt;
import vm.memory.Frame;
import vm.memory.PCB;
import vm.programs.Program;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

// ------------------- V M  - constituida de vm.CPU e MEMORIA -----------------------------------------------
// -------------------------- atributos e construcao da vm.VM -----------------------------------------------
public class SystemOperational {
    private static final Integer FRAME_SIZE = 64;
    private final List<Frame> framesIndex;
    private final Integer wordsPerFrame;
    private int tamMem;
    private Word[] memory;
    private CPU cpu;
    private List<PCB> readyList;
    private List<PCB> consoleRequestList;
    private List<PCB> blockedList;

    public SystemOperational() {
        tamMem = 1024;
        wordsPerFrame = tamMem / FRAME_SIZE;
        readyList = new ArrayList<>();
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
        cpu = new CPU(this, memory, instructionRules);
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

    public boolean handleInterruption(SystemInterrupt interrupt) {
        return interrupt.handleInterrupt(this);
    }

    public void handleProgramChange(CPU cpu) {
        cpu.saveCurrentState();
        PCB pcb = cpu.getCurrentPCB();
        readyList.add(pcb);
        PCB nextPCB = readyList.remove(0);
        cpu.loadStateOf(nextPCB);
    }

    public List<PCB> getConsoleRequestList() {
        return consoleRequestList;
    }

    //Redo
    public void start() {
        int programs = readyList.size();
        for (int i = 0; i < programs || !readyList.isEmpty() ; i++) {
            PCB pcb = readyList.remove(0);
            cpu.setCurrentPCB(pcb);
            cpu.run();
        }
       dumpToFile();
    }

    private void dumpToFile() {
        File file = new File("MemoryDump.txt");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            for (int i = 0; i < memory.length; i++) {
                printWriter.println(i + " - " + memory[i]);
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    p

    public void interruptCpu() {
        try {
            cpu.interrupt();
            cpu.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
