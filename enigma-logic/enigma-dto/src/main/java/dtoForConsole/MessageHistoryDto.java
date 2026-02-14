package dtoForConsole;

public class MessageHistoryDto {
    private String inputMessage;
    private String processedMessage;
    private long processTimeNano;

    public MessageHistoryDto(String inputMessage, String processedMessage, long processTime) {
        this.inputMessage = inputMessage;
        this.processedMessage = processedMessage;
        this.processTimeNano = processTime;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public String getProcessedMessage() {
        return processedMessage;
    }

    public long getProcessTimeNano() {
        return processTimeNano;
    }
}
