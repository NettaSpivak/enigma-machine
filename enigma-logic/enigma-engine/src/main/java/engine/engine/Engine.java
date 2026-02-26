package engine.engine;

import dto.*;
import dtoForConsole.*;
import java.io.InputStream;

public interface Engine {
    String loadXml(InputStream inputStream) throws Exception;
    MachineStatusDto getMachineStatus(); // returns some machine dto
    void codeManual(String sessionId, CodeSnapShotDto codeSnapShotDto);
    void codeAutomatic(String sessionId);
    ProcessMessageDto processMessage(String message, String sessionID);
    void resetCode(String sessionId);
    MachineHistoryDto historyAndStatistics();
    void saveSnapshot(String path);
    void loadSnapshot(String path);
}
