package loader.builder;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.util.Map;

public class MachineComponents {
    private final String name;
    private final String alphabet;
    private final Map<Integer, Rotor> rotors;
    private final Map<String, Reflector> reflectors;
    private final int rotorsCount;

    public MachineComponents(String name, String alphabet, Map<Integer, Rotor> rotors, Map<String, Reflector> reflectors, int rotorsCount) {
        this.name = name;
        this.alphabet = alphabet.toUpperCase();
        this.rotors = rotors;
        this.reflectors = reflectors;
        this.rotorsCount = rotorsCount;
    }

    public String getName() {
        return name;
    }
    public String getAlphabet() {
        return this.alphabet;
    }
    public Map<Integer, Rotor> getRotors() {
        return rotors;
    }
    public Map<String, Reflector> getReflectors() {
        return reflectors;
    }
    public int getRotorsCount() {
        return rotorsCount;
    }
}
