package api.manager;

import api.response.ProcessResponse;
import dal.dbProcessing.DBProcessing;
import dto.ProcessMessageDto;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

import java.util.NoSuchElementException;

@Service
public class ProcessManager {
    private final SessionRegistry sessionRegistry;
    private final DBProcessing dbProcessing;

    public ProcessManager(SessionRegistry sessionRegistry, DBProcessing dbProcessing) {
        this.sessionRegistry = sessionRegistry;
        this.dbProcessing = dbProcessing;
    }

    public ProcessResponse processMessage(String message, String sessionID) throws NoSuchElementException, IllegalArgumentException {
        Session session = sessionRegistry.getSession(sessionID);
        ProcessMessageDto messageDto = session.getEngine().processMessage(message);
        dbProcessing.saveProcessing(session.getMachineName(), sessionID, messageDto.getCodeBeforeProcessing(), message.toUpperCase(), messageDto.getMessage(), messageDto.getDuration());
        return new ProcessResponse(messageDto.getMessage(), messageDto.getCurrentRotorsPositionCompact());
    }
}