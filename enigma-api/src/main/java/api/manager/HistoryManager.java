package api.manager;

import api.response.HistoryResponse;
import api.schemas.HistoryEntry;
import dal.dbProcessing.DBProcessing;
import dto.HistoryDto;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class HistoryManager {
    private final SessionRegistry sessionRegistry;
    private final DBProcessing dbProcessing;

    public HistoryManager(SessionRegistry sessionRegistry, DBProcessing dbProcessing) {
        this.sessionRegistry = sessionRegistry;
        this.dbProcessing = dbProcessing;
    }

    public Map<String, List<HistoryEntry>> getHistory(String sessionID, String machineName) throws IllegalArgumentException {
        HistoryDto historyDto = dbProcessing.getHistoryFromDB(sessionID, machineName);
        return HistoryResponse.fromHistoryDto(historyDto).getHistory();
    }
}