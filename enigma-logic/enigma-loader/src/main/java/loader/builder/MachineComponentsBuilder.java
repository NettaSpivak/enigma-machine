package loader.builder;

import engine.machineRepository.MachineRepository;
import engine.machineRepository.MachineRepositoryImpl;
import loader.generated.*;
import loader.xmlLoader.XmlLoader;
import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

import java.io.InputStream;
import java.util.*;

public class MachineComponentsBuilder {

    public MachineRepository buildMachineComponentsFromXml(InputStream inputStream) throws IllegalArgumentException {
        BTEEnigma bteEnigma = XmlLoader.loadXml(inputStream);
        return buildMachineRepository(bteEnigma);
    }

    private MachineRepository buildMachineRepository(BTEEnigma bteEnigma) throws IllegalArgumentException {
        try {
            String name = bteEnigma.getName().trim();
            String alphabet = bteEnigma.getABC().trim().toUpperCase();
            validateAlphabet(alphabet);
            int rotorsCount = bteEnigma.getRotorsCount().intValue();
            Map<Integer, Rotor> rotors = RotorsBuilder.buildRotors(bteEnigma.getBTERotors(), alphabet, rotorsCount);
            Map<String, Reflector> reflectors = ReflectorsBuilder.buildReflectors(bteEnigma.getBTEReflectors(), alphabet);
            return new MachineRepositoryImpl(name, alphabet, rotors, reflectors, rotorsCount);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("BteEnigma contains illegal arguments: " + e.getMessage(), e);
        }

    }

    private void validateAlphabet(String alphabet) throws IllegalArgumentException {
        if (alphabet == null || alphabet.isEmpty()) {
            throw new IllegalArgumentException("ABC cannot be null or empty.");
        }
        // check for even count of characters
        if (alphabet.length() % 2 != 0) {
            throw new IllegalArgumentException("ABC must have an even number of characters.");
        }
        // check for unique characters
        Set<Character> seen = new HashSet<>();
        for (char c : alphabet.toCharArray()) {
            if (!seen.add(c))
                throw new IllegalArgumentException("ABC contains duplicate character: " + c);
        }
    }
}
