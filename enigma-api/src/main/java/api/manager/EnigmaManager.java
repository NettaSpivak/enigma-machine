package api.manager;

import dtoForConsole.*;
import engine.engine.Engine;
import org.springframework.stereotype.Service;


@Service
public class EnigmaManager {
    private final Engine engine;

    public EnigmaManager(Engine engine) {
        this.engine = engine;
    }

    public void loadXml(String filePath) throws Exception {
        engine.loadXml(filePath.trim());
    }

    public MachineDataDto getMachineData() {
        return engine.showMachineData();
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
