package api.manager;

import api.response.ConfigResponse;
import api.response.ProcessResponse;
import api.schemas.EnigmaManualConfigRequest;
import dto.*;
import dtoForConsole.*;
import engine.engine.Engine;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class EnigmaManager {
    private final Engine engine;

    public EnigmaManager(Engine engine) {
        this.engine = engine;
    }

    public String loadXml(MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }
        return engine.loadXml(file.getInputStream());
    }

    public ConfigResponse getConfig(String sessionID, boolean verbose) {
        MachineStatusDto machineStatus = engine.getMachineStatus();
        return ConfigResponse.fromMachineStatusDto(machineStatus, verbose);
    }

    public void setCodeManual(EnigmaManualConfigRequest request) throws IllegalArgumentException {
        CodeSnapShotDto codeSnapShotDto = request.toCodeSnapShotDto();
        engine.codeManual(request.getSessionID(), codeSnapShotDto);
    }

    public void setCodeAutomatic(String sessionId) throws IllegalArgumentException {
        engine.codeAutomatic(sessionId);
    }

    public ProcessResponse processMessage(String message, String sessionID) throws IllegalArgumentException {
        ProcessMessageDto messageDto = engine.processMessage(message, sessionID);

        return new ProcessResponse(messageDto.getMessage(), messageDto.getCurrentRotorsPositionCompact());
    }

    public void resetCode(String sessionId) {
        engine.resetCode(sessionId);
    }

    public MachineHistoryDto historyAndStatistics() {
        return engine.historyAndStatistics();
    }

    public void saveSnapshot(String path) throws RuntimeException {
        engine.saveSnapshot(path.trim());
    }

    public void loadSnapshot(String path) throws RuntimeException {
        engine.loadSnapshot(path.trim());
    }
}
