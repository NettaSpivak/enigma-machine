package engine.machineRepository;

import loader.builder.MachineComponents;
import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.io.Serializable;
import java.util.Map;

public class MachineRepositoryImpl implements MachineRepository, Serializable {
    private final String alphabet;
    private final Map<Integer, Rotor> rotorsRepository;
    private final Map<String, Reflector> reflectorsRepository;
    private final int numberOfRotorsInUse;

    public MachineRepositoryImpl (MachineComponents machineComponents) {
        this.alphabet = machineComponents.getAlphabet().toUpperCase();
        this.rotorsRepository = machineComponents.getRotors();
        this.reflectorsRepository = machineComponents.getReflectors();
        this.numberOfRotorsInUse = machineComponents.getRotorsCount();
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
