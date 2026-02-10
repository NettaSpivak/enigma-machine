package machine.component.keyboard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class KeyboardImpl implements Keyboard, Serializable {
    private final char[] alphabet;
    private final Map<Character, Integer> charToIndexMap = new HashMap<>();

    public KeyboardImpl(String alphabet) {
        this.alphabet = alphabet.toUpperCase().toCharArray();
        for (int i = 0; i < this.alphabet.length; i++) {
            charToIndexMap.put(this.alphabet[i], i);
        }
    }

    @Override
    public int processChar(char input) throws IllegalArgumentException {
        Integer charInd = charToIndexMap.get(Character.toUpperCase(input));
        if (charInd == null) {
            throw new IllegalArgumentException("Input character " + input + " is not in alphabet");
        }
        return charInd;
    }

    @Override
    public char lightALamp(int output) {
        return alphabet[output];
    }
}
