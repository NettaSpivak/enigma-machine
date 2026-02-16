package engine.engine;

import dto.*;
import dtoForConsole.*;
import java.io.InputStream;

public interface Engine {
    String loadXml(InputStream inputStream) throws Exception;
    MachineStatusDto getMachineStatus(); // returns some machine dto
    void codeManual(CodeSnapShotDto codeSnapShotDto);
    void codeAutomatic();
    MessageDto processMessage(MessageDto messagedto);
    void resetCode();
    MachineHistoryDto historyAndStatistics();
    void saveSnapshot(String path);
    void loadSnapshot(String path);
    boolean haveCode();
}
