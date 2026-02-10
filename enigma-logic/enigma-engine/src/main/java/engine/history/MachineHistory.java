package engine.history;

import machine.component.code.Code;
import machine.component.rotor.Rotor;
import machine.component.code.CodeSnapShot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;


public class MachineHistory implements Serializable {
    private final List<CodeHistory> codeHistory;

    public MachineHistory() {
        this.codeHistory = new ArrayList<>();
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

    public void addMessageToCode(String message, String processedMessage, long processTimeNano) {
        codeHistory.getLast().AddNewMessageToHistory(message, processedMessage, processTimeNano);
    }

    public CodeSnapShot getCurrentCodeSnapShot() {
        if (codeHistory.isEmpty()) {
            return null;
        }
        return this.codeHistory.get(codeHistory.size()-1).getCodeSnapShot();
    }

    public int getTotalNumberOfProcessedMessages() {
        int processedMessages = 0;
        for(CodeHistory codeHistory : this.codeHistory){
            processedMessages += codeHistory.getNumberOfProcessedMessagesInCode();
        }
        return processedMessages;
    }

    public List<CodeHistory> getCodeHistory() {
        return List.copyOf(codeHistory);
    }

}
