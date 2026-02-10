package machine.component.code;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;
import machine.component.plugboard.Plugboard;
import java.io.Serializable;
import java.util.*;

public class CodeImpl implements Code, Serializable {
    private final List<Rotor> rotors;
    private final List<RotorPosition> rotorPositions; // the chosen code positions for each rotor
    private final Reflector reflector;
    private final Plugboard plugboard;

    public CodeImpl(List<Rotor> rotors, List<RotorPosition> rotorPositions, Reflector reflector, Plugboard plugboard) {
        this.rotors = rotors;
        this.rotorPositions = rotorPositions;
        resetRotorsCurrentPositions();
        this.reflector = reflector;
        this.plugboard = plugboard;
    }

    @Override
    public void resetRotorsCurrentPositions() {
        for (RotorPosition rp : this.rotorPositions) {
            rp.getRotor().initializeRotorPosition(rp.getRotorPosition());
        }
    }

    @Override
    public List<Rotor> getRotors() {
        return List.copyOf(this.rotors);
    }

    @Override
    public List<RotorPosition> getRotorsPositionsList() {
        return List.copyOf(this.rotorPositions);
    }

    @Override
    public Reflector getReflector() {
        return this.reflector;
    }

    @Override
    public Plugboard getPlugboard() { return this.plugboard; }

    @Override
    public List<Integer> getRotorsIds() {
        List<Integer> ids = new ArrayList<>();
        for (Rotor rotor : this.rotors) {
            ids.add(rotor.getId());
        }
        return ids;
    }

    @Override
    public List<Character> getRotorsCurrentPositions() {
        List<Character> positions = new ArrayList<>();
        for (Rotor rotor : this.rotors) {
            positions.add(rotor.getPosition());
        }
        return positions;
    }

    @Override
    public List<char[]> getPlugboardPairsList() {
        List<char[]> plugboardPairsList = new ArrayList<>();
        Set<Character> tmpVisited = new HashSet<>();
        for (Map.Entry<Character, Character> entry : plugboard.getWiring().entrySet()) {
            char c1 = entry.getKey();
            char c2 = entry.getValue();
            if (tmpVisited.contains(c1)) {
                continue;
            }
            plugboardPairsList.add(new char[]{c1, c2});
            tmpVisited.add(c1);
            tmpVisited.add(c2);
        }
        return plugboardPairsList;
    }

}