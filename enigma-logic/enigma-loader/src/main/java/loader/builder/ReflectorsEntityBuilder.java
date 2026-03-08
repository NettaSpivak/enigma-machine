package loader.builder;

import dal.entity.MachineReflectorEntity;
import loader.generated.BTEReflect;
import loader.generated.BTEReflector;
import loader.generated.BTEReflectors;
import machine.component.reflector.ReflectorId;
import machine.utils.Utils;

import java.util.*;

public class ReflectorsEntityBuilder {

    public static List<MachineReflectorEntity> buildReflectors(BTEReflectors bteReflectors, String alphabet, UUID machineId) throws IllegalArgumentException {
        try {
            List<MachineReflectorEntity> reflectors = new ArrayList<>();
            if (bteReflectors.getBTEReflector().isEmpty() || bteReflectors.getBTEReflector().size() > 5) {
                throw new IllegalArgumentException("Reflectors count must be between 1-5.");
            }

            Set<String> ids = new HashSet<>();
            for (BTEReflector bteReflector : bteReflectors.getBTEReflector()) {
                String id = bteReflector.getId().trim();
                int idAsInt;
                try {
                    idAsInt = Utils.romanToInt(id);
                    if (idAsInt == 0 || idAsInt > bteReflectors.getBTEReflector().size()) {
                        throw new IllegalArgumentException("ID must be between I to " + Utils.intToRoman(bteReflectors.getBTEReflector().size()));
                    }
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Reflector ID " + id + " is not valid, " + e.getMessage());
                }
                if (!ids.add(id)) {
                    throw new IllegalArgumentException("Duplicate reflector ID found: " + id);
                }

                MachineReflectorEntity entity = buildReflectorEntity(machineId, bteReflector, alphabet);
                reflectors.add(entity);
            }
            return reflectors;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error building reflectors: " + e.getMessage());
        }
    }

    private static MachineReflectorEntity buildReflectorEntity(UUID machineId, BTEReflector reflector, String alphabet) throws IllegalArgumentException {
        try {
            validateReflector(reflector, alphabet);

            StringBuilder input = new StringBuilder();
            StringBuilder output = new StringBuilder();
            for (BTEReflect reflect : reflector.getBTEReflect()) {
                char in = alphabet.charAt(reflect.getInput() - 1);
                char out = alphabet.charAt(reflect.getOutput() - 1);
                input.append(in);
                output.append(out);
            }
            MachineReflectorEntity entity = new MachineReflectorEntity();
            entity.setReflectorId(ReflectorId.valueOf(reflector.getId().trim()));
            entity.setInput(input.toString());
            entity.setOutput(output.toString());
            return entity;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error building reflector :" + e.getMessage());
        }
    }
        private static void validateReflector(BTEReflector reflector, String alphabet) throws IllegalArgumentException {
        int size = alphabet.length() / 2;
        if (reflector.getBTEReflect().size() != size) {
            throw new IllegalArgumentException("Reflector ID " + reflector.getId()
                    + " must have exactly " + size + " reflect entries.");
        }

        boolean[] seen = new boolean[alphabet.length()];
        for (BTEReflect reflect : reflector.getBTEReflect()) {
            int input = reflect.getInput();
            int output = reflect.getOutput();
            if (input < 1 || input > alphabet.length() || output < 1 || output > alphabet.length()) {
                throw new IllegalArgumentException("Reflector ID " + reflector.getId()
                        + " has reflect entry with invalid input/output: input=" + input + ", output=" + output);
            }
            if (input == output) {
                throw new IllegalArgumentException("Reflector ID " + reflector.getId()
                        + " maps a value to itself: " + input);
            }
            if (seen[input - 1]) {
                throw new IllegalArgumentException("Reflector ID " + reflector.getId()
                        + " has duplicate mapping for input: " + input);
            }
            if (seen[output - 1]) {
                throw new IllegalArgumentException("Reflector ID " + reflector.getId()
                        + " has duplicate mapping for output: " + output);
            }
            seen[input - 1] = true;
            seen[output - 1] = true;
        }
    }
}