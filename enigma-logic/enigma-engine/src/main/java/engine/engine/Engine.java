package engine.engine;

import dtoForConsole.*;

public interface Engine {
    void loadXml(String filePath) throws Exception;
    MachineDataDto showMachineData(); // returns some machine dto
    void codeManual(CodeSnapShotDto codeSnapShotDto);
    void codeAutomatic();
    MessageDto processMessage(MessageDto messagedto);
    void resetCode();
    MachineHistoryDto historyAndStatistics();
    void saveSnapshot(String path);
    void loadSnapshot(String path);
    boolean haveCode();
}
