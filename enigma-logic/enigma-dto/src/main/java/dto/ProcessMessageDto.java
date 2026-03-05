package dto;

public class ProcessMessageDto {
    private String message;
    private String currentRotorsPositionCompact;
    private int duration;

    public ProcessMessageDto(String message, String currentRotorsPositionCompact, int duration) {
        this.message = message;
        this.currentRotorsPositionCompact = currentRotorsPositionCompact;
        this.duration = duration;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCurrentRotorsPositionCompact() {
        return this.currentRotorsPositionCompact;
    }

    public int getDuration() {
        return this.duration;
    }
}
