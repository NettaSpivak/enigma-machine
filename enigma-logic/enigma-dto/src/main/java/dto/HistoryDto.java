package dto;

import java.util.List;
import java.util.Map;

public class HistoryDto {
    private  Map<String, List<MessageHistoryDto>> messageHistoryByCodeDescriptionDto;

    public HistoryDto(Map<String, List<MessageHistoryDto>> messageHistoryByCodeDescriptionDto) {
        this.messageHistoryByCodeDescriptionDto = messageHistoryByCodeDescriptionDto;
    }

    public Map<String, List<MessageHistoryDto>> getMessageHistoryByCodeDescriptionDto() {
        return this.messageHistoryByCodeDescriptionDto;
    }
}
