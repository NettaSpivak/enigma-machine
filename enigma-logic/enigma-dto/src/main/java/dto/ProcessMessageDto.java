package dto;

public class ProcessMessageDto {
    private String message;
    private String currentRotorsPositionCompact;

    public ProcessMessageDto(String message, String currentRotorsPositionCompact) {
        this.message = message;
        this.currentRotorsPositionCompact = currentRotorsPositionCompact;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCurrentRotorsPositionCompact() {
        return this.currentRotorsPositionCompact;
    }
}
