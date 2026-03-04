package session;

import engine.engine.Engine;
import engine.engine.EngineImpl;
import engine.machineRepository.MachineRepository;

public class Session {
    private final String sessionId;
    private final String machineName;
    private final Engine engine;

    public Session(String sessionId, String machineName, MachineRepository repository) {
        this.sessionId = sessionId;
        this.machineName = machineName;
        this.engine = new EngineImpl(repository);
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getMachineName() {
        return machineName;
    }

    public Engine getEngine() {
        return engine;
    }
}