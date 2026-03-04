package dto;

import java.util.List;

public class HistoryDto {
    private List<CodeHistoryDto> codeHistoryDto;

    public HistoryDto(List<CodeHistoryDto> codeHistoryDto) {
        this.codeHistoryDto = codeHistoryDto;
    }

    public List<CodeHistoryDto> getCodeHistoryDto() {
        return this.codeHistoryDto;
    }
}
