package api.manager;

import api.response.HistoryResponse;
import dto.HistoryDto;
import engine.engine.Engine;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

@Service
public class HistoryManager {
    private final SessionRegistry sessionRegistry;

    public HistoryManager(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public HistoryResponse getHistory(String sessionID, String machineName) throws IllegalArgumentException{
        if ((sessionID == null && machineName == null) || (sessionID != null && machineName != null)) {
            throw new IllegalArgumentException("Exactly one of sessionID or machineName must be provided");
        }
        if (sessionID != null) {
            Session session = sessionRegistry.getSession(sessionID);
            HistoryDto historyDto = session.getEngine().getHistory(session.getMachineName());
            return HistoryResponse.fromHistoryDto(historyDto);
        }
        // history של כל המכונה (בעתיד דרך DB)
        throw new IllegalArgumentException("Machine history not supported yet");
    }
}