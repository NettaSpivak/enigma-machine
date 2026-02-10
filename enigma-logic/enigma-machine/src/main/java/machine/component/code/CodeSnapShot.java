package machine.component.code;

import java.io.Serializable;
import java.util.List;

public class CodeSnapShot implements Serializable {
    private final List<Integer> rotorIds;
    private final List<Character> rotorPosition;
    private final List<Integer> notchDistanceFromWindow;
    private final String reflectorId;
    private final List<char[]> plugboard;

    public CodeSnapShot(List<Integer> rotorIds, List<Character> rotorPosition, List<Integer> notchDistanceFromWindow, String reflectorId, List<char[]> plugboard) {
        this.rotorIds = rotorIds;
        this.rotorPosition = rotorPosition;
        this.notchDistanceFromWindow = notchDistanceFromWindow;
        this.reflectorId = reflectorId;
        this.plugboard = plugboard;
    }

    public List<Integer> getRotorIds() {
        return List.copyOf(rotorIds);
    }

    public List<Character> getRotorPosition() {
        return List.copyOf(rotorPosition);
    }

    public List<Integer> getNotchDistanceFromWindow() {
        return List.copyOf(notchDistanceFromWindow);
    }

    public String getReflectorId() {
        return reflectorId;
    }

    public List<char[]> getPlugboard() { return plugboard; }
}