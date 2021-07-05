package vm.programs;

import vm.Word;

import java.util.Arrays;

abstract public class Program {
    private final Integer framesNeeded;
    private final String name;

    public Program(String name, Integer framesNeeded) {
        this.name = name;
        this.framesNeeded = framesNeeded;
    }

    public Word[] createProgram(int wordPerFrame) {
        Word[] programWords = loadProgramWords();
        Word[] fullProgramSize = Arrays.copyOf(programWords, framesNeeded * wordPerFrame);
        Arrays.fill(fullProgramSize, programWords.length, fullProgramSize.length, Word.emptyWord());
        return fullProgramSize;
    }

    public String getName() {
        return name;
    }

    protected abstract Word[] loadProgramWords();
}
