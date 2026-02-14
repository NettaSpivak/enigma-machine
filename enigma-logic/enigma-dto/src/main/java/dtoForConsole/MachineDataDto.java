package dtoForConsole;


public class MachineDataDto {
    private int rotorsInSystem;
    private int reflectorsInSystem;
    private int processedMessageCount;
    private CodeSnapShotDto originalCodeSnapShot;
    private CodeSnapShotDto currentCodeSnapShot;

    public MachineDataDto(int rotorsInSystem, int reflectorsInSystem, int processedMessageCount, CodeSnapShotDto originalCodeSnapShot, CodeSnapShotDto currentCodeSnapShot) {
        this.rotorsInSystem = rotorsInSystem;
        this.reflectorsInSystem = reflectorsInSystem;
        this.processedMessageCount = processedMessageCount;
        this.originalCodeSnapShot = originalCodeSnapShot;
        this.currentCodeSnapShot = currentCodeSnapShot;
    }

    public int getRotorsInSystem() {
        return rotorsInSystem;
    }

    public int getReflectorsInSystem() {
        return reflectorsInSystem;
    }

    public int getProcessedMessageCount() {
        return processedMessageCount;
    }

    public CodeSnapShotDto getOriginalCodeSnapShot() {
        return originalCodeSnapShot;
    }

    public CodeSnapShotDto getCurrentCodeSnapShot() {
        return currentCodeSnapShot;
    }
}
