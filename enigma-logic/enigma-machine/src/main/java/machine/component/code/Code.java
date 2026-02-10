package machine.component.code;

import machine.component.plugboard.Plugboard;
import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.io.Serializable;
import java.util.List;

public interface Code {
    List<Rotor> getRotors();
    List<RotorPosition> getRotorsPositionsList();
    Reflector getReflector();
    Plugboard getPlugboard();
    List<Integer> getRotorsIds();
    List<Character> getRotorsCurrentPositions();
    List<char[]> getPlugboardPairsList();
    void resetRotorsCurrentPositions();


    public static class RotorPosition implements Serializable {
        private final Rotor rotor;
        private final char rotorPosition;
        private final int rotorPositionIndex;

        public RotorPosition(Rotor rotor, char rotorPosition) {
            this.rotor = rotor;
            this.rotorPosition = rotorPosition;
            this.rotorPositionIndex = rotor.getCharIndex(rotorPosition);
        }
        public RotorPosition(Rotor rotor) {
            this.rotor = rotor;
            this.rotorPosition = rotor.getPosition();
            this.rotorPositionIndex = rotor.getPositionIndex();
        }

        public Rotor getRotor() {
            return rotor;
        }
        public char getRotorPosition() {
            return rotorPosition;
        }
       public  int getRotorPositionIndex() {
            return rotorPositionIndex;
       }
    }
}
