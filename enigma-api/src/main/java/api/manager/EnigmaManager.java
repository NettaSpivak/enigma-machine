package api.manager;

import api.response.config.ConfigResponse;
import api.response.config.EnigmaCodeStructureResponse;
import dto.*;
import dtoForConsole.*;
import engine.engine.Engine;
import machine.component.code.CodeSnapShot;
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

    public void setCodeManual(CodeSnapShotDto codeSnapShotDto) throws IllegalArgumentException {
        engine.codeManual(codeSnapShotDto);
    }

    public void setCodeAutomatic() throws IllegalArgumentException {
        engine.codeAutomatic();
    }

    public MessageDto processMessage(MessageDto message) throws IllegalArgumentException {
        return engine.processMessage(message);
    }

    public void resetCode() {
        engine.resetCode();
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

    public boolean haveCode() {
        return engine.haveCode();
    }

}
