package dto;

public class MachineStatusDto {
    private final int totalRotors;
    private final int totalReflectors;
    private final int totalProcessedMessages;
    private final CodeSnapShotDto originalCode;
    private final CodeSnapShotDto currentCode;

    public MachineStatusDto(int totalRotors, int totalReflectors, int totalProcessedMessages, CodeSnapShotDto originalCode, CodeSnapShotDto currentCode) {
        this.totalRotors = totalRotors;
        this.totalReflectors = totalReflectors;
        this.totalProcessedMessages = totalProcessedMessages;
        this.originalCode = originalCode;
        this.currentCode = currentCode;
    }

    public int getTotalRotors() {
        return totalRotors;
    }

    public int getTotalReflectors() {
        return totalReflectors;
    }

    public int getTotalProcessedMessages() {
        return totalProcessedMessages;
    }

    public CodeSnapShotDto getOriginalCode() {
        return originalCode;
    }

    public CodeSnapShotDto getCurrentCode() {
        return currentCode;
    }
}
