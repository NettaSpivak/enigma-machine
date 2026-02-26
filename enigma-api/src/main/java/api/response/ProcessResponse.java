package api.response;

import dto.ProcessMessageDto;

public class ProcessResponse {
    private String output;
    private String currentRotorsPositionCompact;


    public ProcessResponse(String output, String currentRotorsPositionCompact) {
        this.output = output;
        this.currentRotorsPositionCompact = currentRotorsPositionCompact;
    }

    public String getOutput() {
        return output;
    }

    public String getCurrentRotorsPositionCompact() {
        return currentRotorsPositionCompact;
    }
}
