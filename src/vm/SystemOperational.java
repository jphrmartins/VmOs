package vm;

import vm.instructionhandle.*;
import vm.interruptions.SystemInterrupt;
import vm.memory.Frame;
import vm.memory.PCB;
import vm.programs.Program;

import java.util.*;

// ------------------- V M  - constituida de vm.CPU e MEMORIA -----------------------------------------------
// -------------------------- atributos e construcao da vm.VM -----------------------------------------------
public class SystemOperational {
    private static final Integer FRAME_SIZE = 64;
    private final List<Frame> framesIndex;
    private final Integer wordsPerFrame;
    public int tamMem;
    public Word[] memory;
    public CPU cpu;
    public List<PCB> readyList;

    public SystemOperational() {
        tamMem = 1024;
        wordsPerFrame = tamMem / 64;
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
        Word[] programWords = program.createProgram();
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
        PCB pcb = new PCB(allocatedFrames, wordsPerFrame);
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

    public void start() {
        int programs = readyList.size();
        for (int i = 0; i < programs || !readyList.isEmpty() ; i++) {
            PCB pcb = readyList.remove(0);
            cpu.setCurrentPCB(pcb);
            cpu.setContext(pcb.getCurrentProgramCounter());
            cpu.run();
        }
        dump();
    }

    private void dump() {
        for (int i = 0; i < memory.length; i++) {
            System.out.println(i + " - " + memory[i]);
        }
    }
}
