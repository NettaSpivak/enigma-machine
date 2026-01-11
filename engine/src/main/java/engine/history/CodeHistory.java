package engine.history;

import machine.component.code.CodeSnapShot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CodeHistory implements Serializable {
    private final CodeSnapShot codeSnapShot;
    private final List<MessageHistory> messageHistory;

    public CodeHistory(CodeSnapShot codeSnapShot) {
        this.codeSnapShot = codeSnapShot;
        this.messageHistory = new ArrayList<>();
    }

    public void AddNewMessageToHistory(String massage, String processedMassage, long processTimeNano) {
        messageHistory.add(new MessageHistory(massage, processedMassage, processTimeNano));

    }

    public CodeSnapShot getCodeSnapShot() {
        return this.codeSnapShot;
    }

    public List<MessageHistory> getMessageHistory() {
        return List.copyOf(this.messageHistory);
    }

    public int getNumberOfProcessedMessagesInCode() {
        return this.messageHistory.size();
    }
}
