package api.response.config;

public class RotorSelectionWithNotchResponse {
    private int rotorNumber;
    private String rotorPosition;
    private int notchDistance;

    public RotorSelectionWithNotchResponse(int rotorNumber, String rotorPosition, int notchDistance) {
        this.rotorNumber = rotorNumber;
        this.rotorPosition = rotorPosition;
        this.notchDistance = notchDistance;
    }

    public int getRotorNumber() {
        return rotorNumber;
    }

    public String getRotorPosition() {
        return rotorPosition;
    }

    public int getNotchDistance() {
        return notchDistance;
    }
}
