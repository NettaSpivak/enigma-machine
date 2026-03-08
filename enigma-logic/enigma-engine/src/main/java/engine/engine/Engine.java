package engine.engine;

import dto.*;
import engine.machineRepository.MachineRepository;

import java.io.InputStream;

public interface Engine {
    MachineStatusDto getMachineStatus(); // returns some machine dto
    void codeManual(CodeSnapShotDto codeSnapShotDto);
    void codeAutomatic();
    ProcessMessageDto processMessage(String message);
    void resetCode();
    HistoryDto getHistory();
}
