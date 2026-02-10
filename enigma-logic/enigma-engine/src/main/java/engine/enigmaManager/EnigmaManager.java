package engine.enigmaManager;

import dto.CodeSnapShotDto;
import dto.MachineDataDto;
import dto.MachineHistoryDto;
import dto.MessageDto;
import engine.engine.Engine;
import engine.engine.EngineImpl;

public class EnigmaManager {
    private Engine engine = new EngineImpl();

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
        engine = engine.loadSnapshot(path.trim());
    }

    public boolean haveCode() {
        return engine.haveCode();
    }

}
