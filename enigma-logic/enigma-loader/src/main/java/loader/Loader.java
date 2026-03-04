package loader;

import engine.machineRepository.MachineRepository;
import loader.builder.MachineComponentsBuilder;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Loader {

    public MachineRepository load(InputStream inputStream) throws Exception {
        try {
            MachineComponentsBuilder machineComponentsBuilder = new MachineComponentsBuilder();
            return machineComponentsBuilder.buildMachineComponentsFromXml(inputStream);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to load XML file: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Failed to load XML file: " + e.getMessage(), e);
        }
    }
}
