package api.response;

import api.schemas.HistoryEntry;
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
        for (Map.Entry<String, List<MessageHistoryDto>> entry : historyDto.getMessageHistoryByCodeDescriptionDto().entrySet()) {
            List<HistoryEntry> messages = new ArrayList<>();
            for (MessageHistoryDto message : entry.getValue()) {
                messages.add(new HistoryEntry(message.getInput(), message.getOutput(), message.getDuration()));
            }
            result.put(entry.getKey(), messages);
        }
        return new HistoryResponse(result);
    }
}