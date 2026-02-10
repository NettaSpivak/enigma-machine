package engine.engine;

import dto.CodeSnapShotDto;
import dto.MachineDataDto;
import dto.MachineHistoryDto;
import dto.MessageDto;

public interface Engine {
    void loadXml(String filePath) throws Exception;
    MachineDataDto showMachineData(); // returns some machine dto
    void codeManual(CodeSnapShotDto codeSnapShotDto);
    void codeAutomatic();
    MessageDto processMessage(MessageDto messagedto);
    void resetCode();
    MachineHistoryDto historyAndStatistics();
    void saveSnapshot(String path);
    Engine loadSnapshot(String path);
    boolean haveCode();
}
