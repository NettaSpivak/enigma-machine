package api.manager;

import api.schemas.CreateSessionRequest;
import engine.engine.EngineImpl;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

@Service
public class SessionManager {
    private final SessionRegistry sessionRegistry;

    public SessionManager(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public String createSession(CreateSessionRequest request) throws IllegalArgumentException {
        if (request == null || request.getMachine() == null || request.getMachine().trim().isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be empty");
        }
        return sessionRegistry.createSession(request.getMachine().trim());
    }

    public void deleteSession(String sessionId) throws IllegalArgumentException {
        sessionRegistry.deleteSession(sessionId);
    }
}