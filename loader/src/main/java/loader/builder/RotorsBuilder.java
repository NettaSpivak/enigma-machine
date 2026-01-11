package loader.builder;

import loader.generated.BTEPositioning;
import loader.generated.BTERotor;
import loader.generated.BTERotors;
import machine.machine.Machine;
import machine.component.rotor.Rotor;
import machine.component.rotor.RotorImpl;
import machine.utils.Utils;

import java.util.*;

public class RotorsBuilder {
    public static Map<Integer, Rotor> buildRotors(BTERotors bteRotors, String alphabet, int rotorsCount) throws IllegalArgumentException {
        try {
            Map<Integer, Rotor> Rotors = new HashMap<>();
            if (bteRotors.getBTERotor().size() < rotorsCount) {
                throw new IllegalArgumentException("Not enough rotors defined in XML for the required rotor count.");
            }
            for (BTERotor bteRotor : bteRotors.getBTERotor()) {
                // validate and create rotor
                int rotorId = bteRotor.getId();
                if (rotorId < 1 || rotorId > bteRotors.getBTERotor().size()) {
                    throw new IllegalArgumentException("Rotor ID " + rotorId + " is not valid, ID must be between 1 to " + bteRotors.getBTERotor().size());
                }
                if (Rotors.containsKey(rotorId)) {
                    throw new IllegalArgumentException("Duplicate rotor ID found: " + rotorId);
                }
                Rotor rotor = buildRotor(bteRotor, alphabet);
                Rotors.put(rotorId, rotor);
            }
            return Rotors;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error building rotors: " + e.getMessage());
        }

    }

    private static Rotor buildRotor (BTERotor bteRotor, String alphabet) throws IllegalArgumentException {
        try {
            validateRotor(bteRotor, alphabet);
            int size = alphabet.length();
            char[] right = new char[size];
            char[] left = new char[size];
            List<BTEPositioning> btePositionings = bteRotor.getBTEPositioning();
            for (int i = 0; i < size; i++) {
                BTEPositioning pos = btePositionings.get(i);
                right[i] = Utils.normalizeLetter(pos.getRight());
                left[i] = Utils.normalizeLetter(pos.getLeft());
            }
            return new RotorImpl(bteRotor.getId(), bteRotor.getNotch() - 1, size, right, left);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error building rotor :" + e.getMessage());
        }
    }

    private static void validateRotor(BTERotor bteRotor, String alphabet) throws IllegalArgumentException {
        int size = alphabet.length();
        // validate notch position
        if (bteRotor.getNotch() <= 0 || bteRotor.getNotch() > size) {
            throw new IllegalArgumentException("Invalid notch position for rotor ID: " + bteRotor.getId());
        }
        // validate positioning entries
        List<BTEPositioning> btePositionings = bteRotor.getBTEPositioning();
        if (btePositionings == null || btePositionings.size() != size) {
            throw new IllegalArgumentException("Rotor ID " + bteRotor.getId() + " must have exactly " + size + " positioning entries.");
        }
        // validate that each letter appears exactly once on both sides
        Set<Character> seenRight = new HashSet<>();
        Set<Character> seenLeft = new HashSet<>();
        for (BTEPositioning pos : btePositionings) {
            char r, l;
            try {
                r = Utils.normalizeLetter(pos.getRight());
                l = Utils.normalizeLetter(pos.getLeft());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Rotor ID " + bteRotor.getId() + " has invalid letter in positioning: " + e.getMessage());
            }

            if (!Utils.isInAlphabet(r, alphabet) || !Utils.isInAlphabet(l, alphabet)) {
                throw new IllegalArgumentException("Rotor ID " + bteRotor.getId()
                        + " has mapping with letter not in alphabet: right=" + r + ", left=" + l);
            }

            if (!seenRight.add(r)) {
                throw new IllegalArgumentException("Rotor ID " + bteRotor.getId()
                        + " has duplicate right letter: " + r);
            }
            if (!seenLeft.add(l)) {
                throw new IllegalArgumentException("Rotor ID " + bteRotor.getId()
                        + " has duplicate left letter: " + l);
            }
        }

        if (seenRight.size() != size || seenLeft.size() != size) {
            throw new IllegalArgumentException("Rotor ID " + bteRotor.getId()
                    + " must contain each alphabet letter exactly once on both sides.");
        }
    }

}

