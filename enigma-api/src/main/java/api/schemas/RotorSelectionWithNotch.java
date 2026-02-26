package api.schemas;

public class RotorSelectionWithNotch {
    private int rotorNumber;
    private String rotorPosition;
    private int notchDistance;

    public RotorSelectionWithNotch(int rotorNumber, String rotorPosition, int notchDistance) {
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

    public void setRotorNumber(int rotorNumber) {
        this.rotorNumber = rotorNumber;
    }

    public void setRotorPosition(String rotorPosition) {
        this.rotorPosition = rotorPosition;
    }

    public void setNotchDistance(int notchDistance) {
        this.notchDistance = notchDistance;
    }
}
