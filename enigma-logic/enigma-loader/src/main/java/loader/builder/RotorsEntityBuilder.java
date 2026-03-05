package loader.builder;

import dal.entity.MachineRotorEntity;
import loader.generated.BTEPositioning;
import loader.generated.BTERotor;
import loader.generated.BTERotors;
import machine.utils.Utils;

import java.util.*;

public class RotorsEntityBuilder {

    public static List<MachineRotorEntity> buildRotors(BTERotors bteRotors, String alphabet, UUID machineId, int rotorsCount) throws IllegalArgumentException {
        List<MachineRotorEntity> rotors = new ArrayList<>();
        if (bteRotors.getBTERotor().size() < rotorsCount) {
            throw new IllegalArgumentException("Not enough rotors defined in XML for the required rotor count.");
        }

        Set<Integer> ids = new HashSet<>();
        for (BTERotor bteRotor : bteRotors.getBTERotor()) {
            int rotorId = bteRotor.getId();
            if (rotorId < 1 || rotorId > bteRotors.getBTERotor().size()) {
                throw new IllegalArgumentException("Rotor ID " + rotorId + " is not valid");
            }
            if (!ids.add(rotorId)) {
                throw new IllegalArgumentException("Duplicate rotor ID found: " + rotorId);
            }

            MachineRotorEntity entity = buildRotorEntity(machineId, bteRotor, alphabet);
            rotors.add(entity);
        }
        return rotors;
    }

    private static MachineRotorEntity buildRotorEntity (UUID machineId, BTERotor bteRotor, String alphabet) throws IllegalArgumentException {
        try {
            validateRotor(bteRotor, alphabet);

            StringBuilder right = new StringBuilder();
            StringBuilder left = new StringBuilder();
            for (BTEPositioning pos : bteRotor.getBTEPositioning()) {
                right.append(Utils.normalizeLetter(pos.getRight()));
                left.append(Utils.normalizeLetter(pos.getLeft()));
            }

            MachineRotorEntity entity = new MachineRotorEntity();
            entity.setRotorId(bteRotor.getId());
            entity.setNotch(bteRotor.getNotch() - 1);
            entity.setWiringRight(right.toString());
            entity.setWiringLeft(left.toString());
            return entity;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error building rotor :" + e.getMessage());
        }
    }

    private static void validateRotor(BTERotor bteRotor, String alphabet) throws IllegalArgumentException {
        int size = alphabet.length();

        if (bteRotor.getNotch() <= 0 || bteRotor.getNotch() > size) {
            throw new IllegalArgumentException("Invalid notch position for rotor ID: " + bteRotor.getId());
        }

        List<BTEPositioning> positionings = bteRotor.getBTEPositioning();
        if (positionings == null || positionings.size() != size) {
            throw new IllegalArgumentException("Rotor ID " + bteRotor.getId() + " must have exactly " + size + " positioning entries.");
        }

        Set<Character> seenRight = new HashSet<>();
        Set<Character> seenLeft = new HashSet<>();
        for (BTEPositioning pos : positionings) {
            char r, l;
            try {
                r = Utils.normalizeLetter(pos.getRight());
                l = Utils.normalizeLetter(pos.getLeft());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Rotor ID " + bteRotor.getId() + " has invalid letter in positioning: " + e.getMessage());
            }

            if (!Utils.isInAlphabet(r, alphabet) || !Utils.isInAlphabet(l, alphabet)) {
                throw new IllegalArgumentException("Rotor ID " + bteRotor.getId() + " has letter not in alphabet");
            }

            if (!seenRight.add(r)) {
                throw new IllegalArgumentException("Duplicate right letter: " + r);
            }

            if (!seenLeft.add(l)) {
                throw new IllegalArgumentException("Duplicate left letter: " + l);
            }
        }
        if (seenRight.size() != size || seenLeft.size() != size) {
            throw new IllegalArgumentException("Rotor ID " + bteRotor.getId() + " must contain each alphabet letter exactly once on both sides.");
        }
    }
}