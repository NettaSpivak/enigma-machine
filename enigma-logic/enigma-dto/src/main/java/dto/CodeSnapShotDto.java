package dto;

import java.util.List;

public class CodeSnapShotDto {
    private List<Integer> rotorIds;
    private List<Character> RotorPosition;
    private List<Integer> notchDistanceFromWindow;
    private String reflectorId;
    private List<char[]> plugboard;

    public CodeSnapShotDto(List<Integer> rotorIds, List<Character> RotorPosition, List<Integer> notchDistanceFromWindow, String reflectorId, List<char[]> plugboard) {
        this.rotorIds = rotorIds;
        this.RotorPosition = RotorPosition;
        this.notchDistanceFromWindow = notchDistanceFromWindow;
        this.reflectorId = reflectorId;
        this.plugboard = plugboard;
    }
    public CodeSnapShotDto(List<Integer> rotorIds, List<Character> RotorPosition, String reflectorId, List<char[]> plugboard) {
        this.rotorIds = rotorIds;
        this.RotorPosition = RotorPosition;
        this.notchDistanceFromWindow = null;
        this.reflectorId = reflectorId;
        this.plugboard = plugboard;
    }

    public List<Integer> getRotorIds() {
        return this.rotorIds;
    }

    public List<Character> getRotorPosition() {
        return this.RotorPosition;
    }

    public List<Integer> getNotchDistanceFromWindow() {
        return this.notchDistanceFromWindow;
    }

    public String getReflectorId() { return this.reflectorId; }

    public List<char[]> getPlugboard() { return this.plugboard; }
}
