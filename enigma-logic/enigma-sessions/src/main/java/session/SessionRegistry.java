package session;

import engine.machineRepository.MachineRepository;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionRegistry {
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();

    public String createSession(MachineRepository machine) throws IllegalArgumentException {
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session(sessionId, machine.getMachineName(), machine);
        sessions.put(sessionId, session);
        return sessionId;
    }

    public Session getSession(String sessionId) throws IllegalArgumentException {
        Session session = sessions.get(sessionId);
        if (session == null) {
            throw new IllegalArgumentException("Unknown sessionID: " + sessionId);
        }
        return session;
    }

    public void deleteSession(String sessionId) throws IllegalArgumentException {
        if (!sessions.containsKey(sessionId)) {
            throw new IllegalArgumentException("Unknown sessionID: " + sessionId);
        }
        sessions.remove(sessionId);
    }

    public boolean containsSession(String sessionId) {
        return sessions.containsKey(sessionId);
    }
}