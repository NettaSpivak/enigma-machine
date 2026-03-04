package api.manager;

import dal.registry.MachineRegistry;
import engine.engine.Engine;
import engine.machineRepository.MachineRepository;
import loader.Loader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LoadManager {
    private final Loader loader;
    private final MachineRegistry machineRegistry;

    public LoadManager(Loader loader, MachineRegistry machineRegistry) {
        this.loader = loader;
        this.machineRegistry = machineRegistry;
    }

    public String loadXml(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        MachineRepository repository = loader.load(file.getInputStream());
        machineRegistry.addMachine(repository);
        return repository.getMachineName();
    }
}