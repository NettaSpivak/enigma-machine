package engine.engine;

import dto.*;
import engine.machineRepository.MachineRepository;

import java.io.InputStream;

public interface Engine {
    MachineStatusDto getMachineStatus(); // returns some machine dto
    void codeManual(String sessionId, CodeSnapShotDto codeSnapShotDto);
    void codeAutomatic(String sessionId);
    ProcessMessageDto processMessage(String message, String sessionID);
    void resetCode(String sessionId);
    HistoryDto getHistory(String machineName);
}
