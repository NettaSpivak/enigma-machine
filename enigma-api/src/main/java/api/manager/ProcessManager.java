package api.manager;

import api.response.ProcessResponse;
import dal.dbProcessing.DBProcessing;
import dto.ProcessMessageDto;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

@Service
public class ProcessManager {
    private final SessionRegistry sessionRegistry;
    private final DBProcessing dbProcessing;

    public ProcessManager(SessionRegistry sessionRegistry, DBProcessing dbProcessing) {
        this.sessionRegistry = sessionRegistry;
        this.dbProcessing = dbProcessing;
    }

    public ProcessResponse processMessage(String message, String sessionID) throws IllegalArgumentException {
        Session session = sessionRegistry.getSession(sessionID);
        ProcessMessageDto messageDto = session.getEngine().processMessage(message, sessionID);
        dbProcessing.saveProcessing(session.getMachineName(), sessionID, messageDto.getCurrentRotorsPositionCompact(), message, messageDto.getMessage(), messageDto.getDuration());
        return new ProcessResponse(messageDto.getMessage(), messageDto.getCurrentRotorsPositionCompact());
    }
}