package machine.component.plugboard;

import java.util.Map;

public class PlugboardImpl implements Plugboard {
    private final Map<Character, Character> wiring;

    public PlugboardImpl(Map<Character, Character> wiring) {
        this.wiring = wiring;
    }

    @Override
    public boolean isCharPlugged(char inputChar) {
        return wiring.containsKey(inputChar);
    }

    @Override
    public char process(char inputChar) {
        return wiring.get(inputChar);
    }

    public Map<Character, Character> getWiring() {
        return wiring;
    }
}
