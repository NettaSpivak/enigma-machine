package engine.history;

import java.io.Serializable;

public class MessageHistory implements Serializable {
    private final String message;
    private final String processedMessage;
    private final long processTimeNano;

    MessageHistory(String message, String processedMessage, long processTime) {
        this.message = message;
        this.processedMessage = processedMessage;
        this.processTimeNano = processTime;
    }

    public String getMessage() {
        return message;
    }

    public String getProcessedMessage() {
        return processedMessage;
    }

    public long getProcessTimeNano() {
        return processTimeNano;
    }
}
