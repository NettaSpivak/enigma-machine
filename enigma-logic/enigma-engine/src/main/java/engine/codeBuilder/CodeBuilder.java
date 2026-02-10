package engine.codeBuilder;

import machine.component.code.Code;

import java.util.List;
import java.util.Map;

public interface CodeBuilder {
    Code buildCode(List<Integer> rotors, List<Character> rotorsPositions, String reflector, List<char[]> plugboard);
}
