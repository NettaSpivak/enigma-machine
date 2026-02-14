package dtoForConsole;

import java.util.List;

public class CodeHistoryDto {
    private CodeSnapShotDto codeSnapShotDto;
    private List<MessageHistoryDto> messageHistoryDto;

    public CodeHistoryDto(CodeSnapShotDto codeSnapShotDto, List<MessageHistoryDto> messageHistoryDto) {
        this.codeSnapShotDto = codeSnapShotDto;
        this.messageHistoryDto = messageHistoryDto;
    }

    public CodeSnapShotDto getCodeSnapShotDto() {
        return this.codeSnapShotDto;
    }
    public List<MessageHistoryDto> getMessageHistoryDto() {
        return this.messageHistoryDto;
    }
}
