package loader.builder;

import dal.entity.MachineEntity;
import loader.generated.BTEEnigma;
import loader.xmlLoader.XmlLoader;
import java.io.InputStream;
import java.util.List;

public class MachineEntitiesBuilder {

    public MachineLoadResult buildFromXml(InputStream inputStream) throws IllegalArgumentException {
        BTEEnigma bteEnigma = XmlLoader.loadXml(inputStream);
        try {
            String name = bteEnigma.getName().trim();
            String alphabet = bteEnigma.getABC().trim().toUpperCase();
            validateAlphabet(alphabet);
            int rotorsCount = bteEnigma.getRotorsCount().intValue();
            MachineEntity machine = new MachineEntity();
            machine.setName(name);
            machine.setAbc(alphabet);
            machine.setRotorsCount(rotorsCount);

            List rotors = RotorsEntityBuilder.buildRotors(bteEnigma.getBTERotors(), alphabet, machine.getId(), rotorsCount);
            List reflectors = ReflectorsEntityBuilder.buildReflectors(bteEnigma.getBTEReflectors(), alphabet, machine.getId());

            return new MachineLoadResult(machine, rotors, reflectors);
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
        boolean[] seen = new boolean[256];
        for (char c : alphabet.toCharArray()) {
            if (seen[c]) {
                throw new IllegalArgumentException("ABC contains duplicate character: " + c);
            }
            seen[c] = true;
        }
    }
}