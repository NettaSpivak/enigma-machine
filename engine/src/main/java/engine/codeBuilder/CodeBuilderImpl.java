package engine.codeBuilder;

import engine.machineRepository.MachineRepository;
import machine.component.code.Code;
import machine.component.code.CodeImpl;
import machine.component.plugboard.Plugboard;
import machine.component.plugboard.PlugboardImpl;
import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;
import machine.utils.Utils;

import java.util.*;

public class CodeBuilderImpl implements CodeBuilder {
    private MachineRepository machineRepository;

    public CodeBuilderImpl(MachineRepository machineRepository) {
        this.machineRepository = machineRepository;
    }

    @Override
    public Code buildCode(List<Integer> rotors, List<Character> rotorsPositions, String reflector, List<char[]> plugboard) throws IllegalArgumentException{
       try {
           List<Rotor> rotorsList = getRotors(rotors);
           if (rotorsList.size() != machineRepository.getNumberOfRotorsInUse()) {
               throw new IllegalArgumentException("Exactly " + machineRepository.getNumberOfRotorsInUse() + " rotors must be specified.");
           }
           List<Code.RotorPosition> rotorPositionsList = getRotorsPositions(rotorsPositions, rotorsList);
           Reflector reflectorObj = getReflector(reflector);
           Collections.reverse(rotorsList);
           Collections.reverse(rotorPositionsList);
           Plugboard plugboardObj = BuildPlugboard(plugboard, machineRepository.getAlphabet());
           return new CodeImpl(rotorsList, rotorPositionsList, reflectorObj, plugboardObj);
       } catch (IllegalArgumentException e) {
           throw new IllegalArgumentException("Error building code: " + e.getMessage());
       }
    }

    private List<Rotor> getRotors(List<Integer> rotors) throws  IllegalArgumentException {
        List<Rotor> rotorList = new ArrayList<>();
        Set<Integer> rotorIdsSet = new HashSet<>();
        // get rotors from the rotors repository
        for (int rotorId : rotors) {
            if (rotorIdsSet.contains(rotorId)) {
                throw new IllegalArgumentException("Duplicate rotor ID found: " + rotorId);
            }
            rotorList.add(machineRepository.getRotorById(rotorId));
            rotorIdsSet.add(rotorId);
        }
        return rotorList;
    }

    private List<Code.RotorPosition> getRotorsPositions(List<Character> rotorsPositions, List<Rotor> rotorList) throws IllegalArgumentException {
        // get rotors positions
        List<Code.RotorPosition> rotorPositionList = new ArrayList<>();
        if (rotorsPositions.size() != rotorList.size()) {
            throw new IllegalArgumentException("Rotor positions count doesn't mach rotors count.");
        }
        for (int i=0; i<rotorsPositions.size(); i++) {
            Character position = rotorsPositions.get(i);
            // validate positions
            if (!Utils.isInAlphabet(position, machineRepository.getAlphabet())) {
                throw new IllegalArgumentException("Invalid rotor position: " + position);
            }
            rotorPositionList.add(new Code.RotorPosition(rotorList.get(i), position));
        }
        return rotorPositionList;
    }

    private Reflector getReflector(String reflector) {
        // get reflector from the reflector repository
        String reflectorId = reflector.trim().toUpperCase();
        return machineRepository.getReflectorById(reflectorId);
    }

    private Plugboard BuildPlugboard(List<char[]> plugboard, String alphabet) throws IllegalArgumentException {
        Map<Character, Character> wiring = new HashMap<>();
        Set<Character> seenChars = new HashSet<>();

        // validate and build plugboard map
        for (int i = 0; i < plugboard.size(); i++) {
            char c1 = Utils.normalizeLetter(String.valueOf(plugboard.get(i)[0]));
            char c2 = Utils.normalizeLetter(String.valueOf(plugboard.get(i)[1]));
            if (!Utils.isInAlphabet(c1, alphabet) || !Utils.isInAlphabet(c2, alphabet)) {
                throw new IllegalArgumentException("Plugboard contains character not in alphabet: " + c1 + " or " + c2);
            }
            if (c1 == c2) {
                throw new IllegalArgumentException("Plugboard cannot map a character to itself: " + c1);
            }
            if (seenChars.contains(c1) || seenChars.contains(c2)) {
                throw new IllegalArgumentException("Plugboard contains duplicate mapping for character: " + c1 + " or " + c2);
            }
            wiring.put(c1, c2);
            wiring.put(c2, c1);
            seenChars.add(c1);
            seenChars.add(c2);
        }
        return new PlugboardImpl(wiring);
    }
}
