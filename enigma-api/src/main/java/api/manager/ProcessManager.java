package api.manager;

import api.response.ProcessResponse;
import dto.ProcessMessageDto;
import engine.engine.Engine;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

@Service
public class ProcessManager {
    private final SessionRegistry sessionRegistry;

    public ProcessManager(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public ProcessResponse processMessage(String message, String sessionID) throws IllegalArgumentException {
        Session session = sessionRegistry.getSession(sessionID);
        ProcessMessageDto messageDto = session.getEngine().processMessage(message, sessionID);
        return new ProcessResponse(messageDto.getMessage(), messageDto.getCurrentRotorsPositionCompact());
    }
}