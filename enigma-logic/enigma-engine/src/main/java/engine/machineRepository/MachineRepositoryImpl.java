package engine.machineRepository;

import machine.component.reflector.Reflector;
import machine.component.reflector.ReflectorImpl;
import machine.component.rotor.Rotor;
import machine.component.rotor.RotorImpl;
import java.util.HashMap;
import java.util.Map;

public class MachineRepositoryImpl implements MachineRepository {
    private final String machineName;
    private final String alphabet;
    private final Map<Integer, Rotor> rotorsRepository;
    private final Map<String, Reflector> reflectorsRepository;
    private final int numberOfRotorsInUse;

    public MachineRepositoryImpl (String machineName, String alphabet, Map<Integer, Rotor> rotors, Map<String, Reflector> reflectors, int rotorsCount) {
        this.machineName = machineName;
        this.alphabet = alphabet;
        this.rotorsRepository = rotors;
        this.reflectorsRepository = reflectors;
        this.numberOfRotorsInUse = rotorsCount;
    }

    public MachineRepositoryImpl(MachineRepositoryImpl other) {
        this.machineName = other.machineName;
        this.alphabet = other.alphabet;
        this.numberOfRotorsInUse = other.numberOfRotorsInUse;
        this.rotorsRepository = new HashMap<>();
        for (Map.Entry<Integer, Rotor> entry : other.rotorsRepository.entrySet()) {
            this.rotorsRepository.put(entry.getKey(), new RotorImpl((RotorImpl) entry.getValue()));
        }
        this.reflectorsRepository = new HashMap<>();
        for (Map.Entry<String, Reflector> entry : other.reflectorsRepository.entrySet()) {
            this.reflectorsRepository.put(entry.getKey(), new ReflectorImpl((ReflectorImpl) entry.getValue()));
        }
    }

    @Override
    public String getMachineName() {
        return this.machineName;
    }

    @Override
    public String getAlphabet() {
        return this.alphabet;
    }

    @Override
    public int getNumberOfRotorsInUse() {
        return numberOfRotorsInUse;
    }


    @Override
    public Rotor getRotorById(int id) throws IllegalArgumentException {
        Rotor rotor = rotorsRepository.get(id);
        if (rotor == null) {
            throw new IllegalArgumentException("Rotor with ID " + id + " does not exist in repository");
        }
        return rotor;
    }

    @Override
    public Reflector getReflectorById(String id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Reflector ID cannot be null");
        }
        String key = id.trim().toUpperCase(); // כי ב‑MachineBuilder שמרת ככה
        Reflector reflector = reflectorsRepository.get(key);
        if (reflector == null) {
            throw new IllegalArgumentException("Reflector with ID " + key + " does not exist in repository");
        }
        return reflector;
    }

    @Override
    public int getNumberOfDefinedRotors() {
        return rotorsRepository.size();
    }

    @Override
    public  int getNumberOfDefinedReflectors() {
        return reflectorsRepository.size();
    }


}
