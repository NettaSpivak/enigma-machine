package dal.dbProcessing;

import dal.entity.MachineEntity;
import dal.entity.ProcessingEntity;
import dal.repository.MachineRepository;
import dal.repository.ProcessingRepository;
import org.springframework.stereotype.Component;

@Component
public class DBProcessing {
    private final ProcessingRepository processingRepository;
    private final MachineRepository machineRepository;

    public DBProcessing(ProcessingRepository processingRepository, MachineRepository machineRepository) {
        this.processingRepository = processingRepository;
        this.machineRepository = machineRepository;
    }

    public void saveProcessing(String machineName, String sessionId, String code, String input, String output, int time) throws IllegalArgumentException {
        MachineEntity machine = machineRepository.findByName(machineName).orElseThrow(() ->
                                new IllegalArgumentException("Failed to save process to DB: Unknown machine"));

        ProcessingEntity entity = new ProcessingEntity();
        entity.setMachineId(machine.getId());
        entity.setSessionId(sessionId);
        entity.setCode(code);
        entity.setInput(input);
        entity.setOutput(output);
        entity.setTime(time);
        processingRepository.save(entity);
    }
}