package api.manager;

import loader.Loader;
import loader.builder.MachineLoadResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LoadManager {
    private final Loader loader;
    private final MachineManager machineManager;

    public LoadManager(Loader loader, MachineManager machineManager, MachineRegistry machineRegistry) {
        this.loader = loader;
        this.machineManager = machineManager;
    }

    public String loadXml(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        MachineLoadResult machine = loader.load(file.getInputStream());
        machineManager.saveMachine(machine);
        return machine.getMachine().getName();
    }
}