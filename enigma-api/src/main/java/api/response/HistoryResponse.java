package api.response;

import api.schemas.HistoryEntry;
import dto.CodeHistoryDto;
import dto.HistoryDto;
import dto.MessageHistoryDto;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class HistoryResponse {
    private Map<String, List<HistoryEntry>> history;

    public HistoryResponse(Map<String, List<HistoryEntry>> history) {
        this.history = history;
    }

    public Map<String, List<HistoryEntry>> getHistory() {
        return history;
    }

    public static HistoryResponse fromHistoryDto(HistoryDto historyDto) {
        Map<String, List<HistoryEntry>> result = new LinkedHashMap<>();

        int index = 1;
        for (CodeHistoryDto codeHistory : historyDto.getCodeHistoryDto()) {
            String key = "code_" + index++;
            List<HistoryEntry> messages = new ArrayList<>();
            for (MessageHistoryDto message : codeHistory.getMessageHistoryDto()) {
                messages.add(new HistoryEntry(message.getInput(), message.getOutput(), message.getDuration()));
            }
            result.put(key, messages);
        }

        return new HistoryResponse(result);
    }
}