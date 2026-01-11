package loader.builder;

import loader.generated.BTEReflect;
import loader.generated.BTEReflector;
import loader.generated.BTEReflectors;
import machine.component.reflector.Reflector;
import machine.component.reflector.ReflectorImpl;
import machine.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectorsBuilder {

    public static Map<String, Reflector> buildReflectors(BTEReflectors bteReflectors, String alphabet) throws IllegalArgumentException {
       try {
           Map<String, Reflector> reflectors = new HashMap<>();
           if (bteReflectors.getBTEReflector().isEmpty() || bteReflectors.getBTEReflector().size() > 5) {
               throw new IllegalArgumentException("Reflectors Count must be between 1-5.");
           }
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
               if (reflectors.containsKey(id)) {
                   throw new IllegalArgumentException("Duplicate reflector ID found: " + id);
               }
               Reflector reflector = buildReflector(bteReflector, alphabet);
               reflectors.put(id, reflector);
           }
           return reflectors;
       } catch (IllegalArgumentException e) {
           throw new IllegalArgumentException("Error building reflectors: " + e.getMessage());
       }
    }

    private static Reflector buildReflector(BTEReflector reflector, String alphabet) {
       try {
           validateReflector(reflector, alphabet);
           int size = alphabet.length();
           int[] mapping = new int[size];
           for (BTEReflect bteReflect : reflector.getBTEReflect()) {
               mapping[bteReflect.getInput() - 1] = bteReflect.getOutput() - 1;
               mapping[bteReflect.getOutput() - 1] = bteReflect.getInput() - 1;
           }
           return new ReflectorImpl(reflector.getId().trim(), mapping);
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
        List<BTEReflect> bteReflects = reflector.getBTEReflect();
        boolean[] seen = new boolean[alphabet.length()];
        for (BTEReflect bteReflect : bteReflects) {
            int input = bteReflect.getInput();
            int output = bteReflect.getOutput();
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
