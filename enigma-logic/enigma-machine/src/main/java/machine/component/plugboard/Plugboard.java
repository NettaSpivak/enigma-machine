package machine.component.plugboard;

import java.util.Map;

public interface Plugboard {
    boolean isCharPlugged(char inputChar);
    char process(char inputChar);
    Map<Character, Character> getWiring();
}
