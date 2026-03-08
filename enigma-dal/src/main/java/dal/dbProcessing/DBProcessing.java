package dal.dbProcessing;

import dal.entity.MachineEntity;
import dal.entity.ProcessingEntity;
import dal.repository.MachineRepository;
import dal.repository.ProcessingRepository;
import dto.HistoryDto;
import dto.MessageHistoryDto;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class DBProcessing {
    private final ProcessingRepository processingRepository;
    private final MachineRepository machineRepository;

    public DBProcessing(ProcessingRepository processingRepository, MachineRepository machineRepository) {
        this.processingRepository = processingRepository;
        this.machineRepository = machineRepository;
    }

    public void saveProcessing(String machineName, String sessionId, String code, String input, String output, int time) throws RuntimeException {
        MachineEntity machine = machineRepository.findByName(machineName).orElseThrow(() ->
                                new RuntimeException("Failed to save process to DB: Unknown machine"));

        ProcessingEntity entity = new ProcessingEntity();
        entity.setMachineId(machine.getId());
        entity.setSessionId(sessionId);
        entity.setCode(code);
        entity.setInput(input);
        entity.setOutput(output);
        entity.setTime(time);
        processingRepository.save(entity);
    }

    public HistoryDto getHistoryFromDB(String sessionId, String machineName) throws IllegalArgumentException {
        if ((sessionId == null && machineName == null) || (sessionId != null && machineName != null)) {
            throw new IllegalArgumentException("Exactly one of sessionID or machineName must be provided");
        }
        List<ProcessingEntity> processing;
        if (sessionId != null) {
            processing = processingRepository.findBySessionId(sessionId);
        } else {
            MachineEntity machine = machineRepository.findByName(machineName).orElseThrow(() ->
                    new IllegalArgumentException("Unknown machine name: " + machineName));
            processing = processingRepository.findByMachineId(machine.getId());
        }
        Map<String, List<MessageHistoryDto>> processingGroupedByCode = new LinkedHashMap<>();
        for (ProcessingEntity entity : processing) {
            MessageHistoryDto message = new MessageHistoryDto(entity.getInput(), entity.getOutput(), (int) entity.getTime());
            if (!processingGroupedByCode.containsKey(entity.getCode())) {
                processingGroupedByCode.put(entity.getCode(), new ArrayList<>());
            }
            processingGroupedByCode.get(entity.getCode()).add(message);
        }
        return new HistoryDto(processingGroupedByCode);
    }
}