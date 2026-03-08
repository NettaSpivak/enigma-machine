package dto;

public class ProcessMessageDto {
    private String message;
    private String currentRotorsPositionCompact;
    private String codeBeforeProcessing;
    private int duration;

    public ProcessMessageDto(String message, String currentRotorsPositionCompact, String codeBeforeProcessing, int duration) {
        this.message = message;
        this.currentRotorsPositionCompact = currentRotorsPositionCompact;
        this.codeBeforeProcessing = codeBeforeProcessing;
        this.duration = duration;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCurrentRotorsPositionCompact() {
        return this.currentRotorsPositionCompact;
    }

    public String getCodeBeforeProcessing() {
        return this.codeBeforeProcessing;
    }

    public int getDuration() {
        return this.duration;
    }
}
