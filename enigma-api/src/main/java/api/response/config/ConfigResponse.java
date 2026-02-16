package api.response.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import dto.MachineStatusDto;
import static api.response.config.EnigmaCodeStructureResponse.fromCodeSnapShotDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigResponse {
    int toalRotors;
    int totalReflectors;
    int totalProcessedMessages;
    EnigmaCodeStructureResponse originalCode;
    EnigmaCodeStructureResponse currentRotorsPosition;
    String originalCodeCompact;
    String currentRotorsPositionCompact;

    public ConfigResponse(int toalRotors, int totalReflectors, int totalProcessedMessages, EnigmaCodeStructureResponse originalCode, EnigmaCodeStructureResponse currentRotorsPosition, String originalCodeCompact, String currentRotorsPositionCompact) {
        this.toalRotors = toalRotors;
        this.totalReflectors = totalReflectors;
        this.totalProcessedMessages = totalProcessedMessages;
        this.originalCode = originalCode;
        this.currentRotorsPosition = currentRotorsPosition;
        this.originalCodeCompact = originalCodeCompact;
        this.currentRotorsPositionCompact = currentRotorsPositionCompact;
    }

    public static ConfigResponse fromMachineStatusDto(MachineStatusDto machine, boolean verbose) {
        if (machine == null) {
            throw new IllegalArgumentException("Machine not loaded");
        }

        String originalCompact = machine.getOriginalCode().toCompactString();
        String currentCompact = machine.getCurrentCode().toPositionsWithNotchCompact();
        if (verbose) {
            return new ConfigResponse(machine.getTotalRotors(), machine.getTotalReflectors(), machine.getTotalProcessedMessages(),
                    fromCodeSnapShotDto(machine.getOriginalCode()), fromCodeSnapShotDto(machine.getCurrentCode()), originalCompact, currentCompact);
        }
        return new ConfigResponse(machine.getTotalRotors(), machine.getTotalReflectors(), machine.getTotalProcessedMessages(),
                null, null, originalCompact, currentCompact);
    }
}
