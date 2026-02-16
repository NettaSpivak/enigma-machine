package dto;

import java.util.List;

public class CodeSnapShotDto {
    private final List<Integer> rotorIds;
    private final List<Character> rotorPositions;
    private final List<Integer> notchDistanceFromWindow;
    private final String reflectorId;
    private final List<char[]> plugboard;

    public CodeSnapShotDto(List<Integer> rotorIds, List<Character> rotorPositions, List<Integer> notchDistanceFromWindow, String reflectorId, List<char[]> plugboard) {
        this.rotorIds = rotorIds;
        this.rotorPositions = rotorPositions;
        this.notchDistanceFromWindow = notchDistanceFromWindow;
        this.reflectorId = reflectorId;
        this.plugboard = plugboard;
    }

    public List<Integer> getRotorIds() {
        return rotorIds;
    }

    public List<Character> getRotorPositions() {
        return rotorPositions;
    }

    public List<Integer> getNotchDistanceFromWindow() {
        return notchDistanceFromWindow;
    }

    public String getReflectorId() {
        return reflectorId;
    }

    public List<char[]> getPlugboard() {
        return plugboard;
    }

    public String toCompactString() {
        StringBuilder codeData = new StringBuilder();
        codeData.append("<");
        for (int i = 0; i < rotorIds.size(); i++) {
            codeData.append(rotorIds.get(i));
            if (i != rotorIds.size() - 1) {
                codeData.append(",");
            }
        }
        codeData.append("><");
        codeData.append(toPositionsWithNotchCompact());
        codeData.append("><").append(reflectorId).append("><");
        if (plugboard != null && !plugboard.isEmpty()) {
            for (int i = 0; i < plugboard.size(); i++) {
                codeData.append(plugboard.get(i)[0]).append("|").append(plugboard.get(i)[1]);
                if (i != plugboard.size() - 1) {
                    codeData.append(",");
                }
            }
        }
        codeData.append(">");
        return codeData.toString();
    }

    public String toPositionsWithNotchCompact() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rotorPositions.size(); i++) {
            sb.append(rotorPositions.get(i)).append("(").append(notchDistanceFromWindow.get(i)).append(")");
            if (i != rotorPositions.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
