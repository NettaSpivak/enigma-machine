package api.manager;

import api.schemas.CreateSessionRequest;
import engine.machineRepository.MachineRepository;
import org.springframework.stereotype.Service;
import session.SessionRegistry;

@Service
public class SessionManager {
    private final SessionRegistry sessionRegistry;
    private final MachineManager machineManager;

    public SessionManager(SessionRegistry sessionRegistry, MachineManager machineManager) {
        this.sessionRegistry = sessionRegistry;
        this.machineManager = machineManager;
    }

    public String createSession(CreateSessionRequest request) throws IllegalArgumentException {
        if (request == null || request.getMachine() == null || request.getMachine().trim().isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be empty");
        }
        MachineRepository machine = machineManager.loadMachineFromDB(request);
        return sessionRegistry.createSession(machine);
    }

    public void deleteSession(String sessionId) throws IllegalArgumentException {
        sessionRegistry.deleteSession(sessionId);
    }
}