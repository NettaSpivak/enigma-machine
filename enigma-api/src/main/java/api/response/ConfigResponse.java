package api.response;

import api.schemas.EnigmaCodeStructure;
import com.fasterxml.jackson.annotation.JsonInclude;
import dto.MachineStatusDto;
import static api.schemas.EnigmaCodeStructure.fromCodeSnapShotDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfigResponse {
    private int totalRotors;
    private int totalReflectors;
    private int totalProcessedMessages;
    private EnigmaCodeStructure originalCode;
    private EnigmaCodeStructure currentRotorsPosition;
    private String originalCodeCompact;
    private String currentRotorsPositionCompact;

    public ConfigResponse(int totalRotors, int totalReflectors, int totalProcessedMessages, EnigmaCodeStructure originalCode, EnigmaCodeStructure currentRotorsPosition, String originalCodeCompact, String currentRotorsPositionCompact) {
        this.totalRotors = totalRotors;
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
        String originalCompact = "";
        String currentCompact = "";
        if (machine.getOriginalCode() != null && machine.getCurrentCode() != null) {
            originalCompact = machine.getOriginalCode().toCompactString();
            currentCompact = machine.getCurrentCode().toPositionsWithNotchCompact();
        }
        if (verbose) {
            return new ConfigResponse(machine.getTotalRotors(), machine.getTotalReflectors(), machine.getTotalProcessedMessages(),
                    fromCodeSnapShotDto(machine.getOriginalCode()), fromCodeSnapShotDto(machine.getCurrentCode()), originalCompact, currentCompact);
        }
        return new ConfigResponse(machine.getTotalRotors(), machine.getTotalReflectors(), machine.getTotalProcessedMessages(),
                null, null, originalCompact, currentCompact);
    }

    public int getTotalReflectors() {
        return totalReflectors;
    }

    public void setTotalReflectors(int totalReflectors) {
        this.totalReflectors = totalReflectors;
    }

    public int getTotalRotors() {
        return totalRotors;
    }

    public void setTotalRotors(int toalRotors) {
        this.totalRotors = toalRotors;
    }

    public int getTotalProcessedMessages() {
        return totalProcessedMessages;
    }

    public void setTotalProcessedMessages(int totalProcessedMessages) {
        this.totalProcessedMessages = totalProcessedMessages;
    }

    public EnigmaCodeStructure getOriginalCode() {
        return originalCode;
    }

    public void setOriginalCode(EnigmaCodeStructure originalCode) {
        this.originalCode = originalCode;
    }

    public EnigmaCodeStructure getCurrentRotorsPosition() {
        return currentRotorsPosition;
    }

    public void setCurrentRotorsPosition(EnigmaCodeStructure currentRotorsPosition) {
        this.currentRotorsPosition = currentRotorsPosition;
    }

    public String getOriginalCodeCompact() {
        return originalCodeCompact;
    }

    public void setOriginalCodeCompact(String originalCodeCompact) {
        this.originalCodeCompact = originalCodeCompact;
    }

    public String getCurrentRotorsPositionCompact() {
        return currentRotorsPositionCompact;
    }

    public void setCurrentRotorsPositionCompact(String currentRotorsPositionCompact) {
        this.currentRotorsPositionCompact = currentRotorsPositionCompact;
    }
}
