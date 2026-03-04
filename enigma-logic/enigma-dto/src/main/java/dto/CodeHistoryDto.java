package dto;

import java.util.List;

public class CodeHistoryDto {
    private List<MessageHistoryDto> messageHistoryDto;

    public CodeHistoryDto(List<MessageHistoryDto> messageHistoryDto) {
        this.messageHistoryDto = messageHistoryDto;
    }

    public List<MessageHistoryDto> getMessageHistoryDto() {
        return this.messageHistoryDto;
    }
}
