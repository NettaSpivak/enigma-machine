package loader;

import loader.builder.MachineEntitiesBuilder;
import loader.builder.MachineLoadResult;
import java.io.InputStream;

public class Loader {

    public MachineLoadResult load(InputStream inputStream) throws Exception {
        try {
            MachineEntitiesBuilder machineEntitiesBuilder = new MachineEntitiesBuilder();
            return machineEntitiesBuilder.buildFromXml(inputStream);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Failed to load XML file: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new Exception("Failed to load XML file: " + e.getMessage(), e);
        }
    }
}
