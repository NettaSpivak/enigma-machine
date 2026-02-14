package dtoForConsole;

import java.util.List;

public class MachineHistoryDto {
    private List<CodeHistoryDto> codeHistoryDto;

    public MachineHistoryDto(List<CodeHistoryDto> codeHistoryDto) {
        this.codeHistoryDto = codeHistoryDto;
    }

    public List<CodeHistoryDto> getCodeHistoryDto() {
        return this.codeHistoryDto;
    }
}
