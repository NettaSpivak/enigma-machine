package api.schemas;

public class RotorSelection {
    private int rotorNumber;
    private String rotorPosition;

    public int getRotorNumber() {
        return rotorNumber;
    }

    public String getRotorPosition() {
        return rotorPosition;
    }

    public void setRotorNumber(int rotorNumber) {
        this.rotorNumber = rotorNumber;
    }

    public void setRotorPosition(String rotorPosition) {
        this.rotorPosition = rotorPosition;
    }
}
