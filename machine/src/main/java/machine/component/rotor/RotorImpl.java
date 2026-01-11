package machine.component.rotor;

import java.io.Serializable;

public class RotorImpl implements Rotor, Serializable {
    private final int id;
    private final int notchPosition;
    private final char[] right;
    private final char[] left;
    private final int alphabetSize;
    private int currentPosition;

    public RotorImpl(int id, int notchPosition, int alphabetSize, char[] right, char[] left){
        this.id = id;
        this.notchPosition = notchPosition;
        this.alphabetSize = alphabetSize;
        this.right = right;
        this.left = left;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public char getPosition(){
        return right[currentPosition];
    }

    public int getPositionIndex(){
        return this.currentPosition;
    }

    @Override
    public void initializeRotorPosition(char positionByChar) throws IllegalArgumentException {
        char target = Character.toUpperCase(positionByChar);
        for (int i = 0; i < alphabetSize; i++) {
            if (right[i] == target) {
                this.currentPosition = i;
                return;
            }
        }
        throw  new IllegalArgumentException("Invalid position provided");
    }

    @Override
    public int process(int input, Direction direction) {
        // shift the input index by the rotor position
        int shiftedInput = (input + currentPosition) % alphabetSize;
        char letter;
        char[] targetArray;
        if (direction == Direction.FORWARD) {
            letter = right[shiftedInput];
            targetArray = left;
        } else {
            letter = left[shiftedInput];
            targetArray = right;
        }
        for (int i = 0; i < alphabetSize; i++) {
            // find the index of the letter in the target array
            if (targetArray[i] == letter) {
                // reverse shift the output index by the rotor position
                int output = (i - currentPosition + alphabetSize) % alphabetSize;
                return output;
            }
        }
        throw new IllegalArgumentException("Invalid input character index: " + input);
    }

    @Override
    public boolean advance() {
        currentPosition = (currentPosition + 1) % alphabetSize;
        return currentPosition == notchPosition;
    }

    @Override
    public int calculateNotchDistanceFromWindow() {
        if (currentPosition <= notchPosition) {
            return notchPosition - currentPosition;
        } else {
            return alphabetSize - (currentPosition - notchPosition);
        }
    }

    @Override
    public int getCharIndex(char c) {
        char target = Character.toUpperCase(c);
        for (int i = 0; i < alphabetSize; i++) {
            if (right[i] == target) {
                return i;
            }
        }
        throw new IllegalArgumentException("Character " + c + " not found in rotor alphabet");
    }
}
