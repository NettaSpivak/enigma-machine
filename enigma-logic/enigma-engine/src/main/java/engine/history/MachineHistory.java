package engine.history;

import machine.component.code.Code;
import machine.component.rotor.Rotor;
import machine.component.code.CodeSnapShot;
import java.util.*;

public class MachineHistory {
    private final List<CodeHistory> codeHistory;
    private final Map<String, List<MessageHistory>> messageHistoryByCodeDescription;

    public MachineHistory() {
        this.codeHistory = new ArrayList<>();
        this.messageHistoryByCodeDescription = new LinkedHashMap<>();
    }

    public void addNewCodeToHistory(Code code) {
        List<Integer> rotorIds = code.getRotorsIds();
        List<Character> rotorInitialPositions = new ArrayList<>();
        for (Code.RotorPosition rotorPosition : code.getRotorsPositionsList()) {
            rotorInitialPositions.add(rotorPosition.getRotorPosition());
        }
        List<Integer> notchDistanceFromWindow = new ArrayList<>();
        for (Rotor rotor : code.getRotors()) {
            notchDistanceFromWindow.add(rotor.calculateNotchDistanceFromWindow());
        }
        List<char[]> plugboardPairsList = code.getPlugboardPairsList();
        CodeSnapShot newCodeSnapShot = new CodeSnapShot(rotorIds, rotorInitialPositions, notchDistanceFromWindow, code.getReflector().getId(), plugboardPairsList);
        codeHistory.add(new CodeHistory(newCodeSnapShot));
    }

    public void SaveMessageToHistory(String codeBeforeProcessing, String message, String processedMessage, int processTimeNano) {
        MessageHistory newMessage = new MessageHistory(message, processedMessage, processTimeNano);
        if (!messageHistoryByCodeDescription.containsKey(codeBeforeProcessing)) {
            messageHistoryByCodeDescription.put(codeBeforeProcessing, new ArrayList<>());
        }
        messageHistoryByCodeDescription.get(codeBeforeProcessing).add(newMessage);
    }

    public CodeSnapShot getCurrentCodeSnapShot() {
        if (codeHistory.isEmpty()) {
            return null;
        }
        return this.codeHistory.get(codeHistory.size()-1).getCodeSnapShot();
    }

    public int getTotalNumberOfProcessedMessages() {
        int totalMessages = 0;
        for (List<MessageHistory> messageHistories : messageHistoryByCodeDescription.values()) {
            totalMessages += messageHistories.size();
        }
        return totalMessages;
    }

    public List<CodeHistory> getCodeHistory() {
        return List.copyOf(codeHistory);
    }

    public Map<String, List<MessageHistory>> getMessageHistoryByCodeDescription() {
        return Map.copyOf(messageHistoryByCodeDescription);
    }
}
