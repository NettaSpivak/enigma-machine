package api.response;

public class SessionResponse {
    private final String sessionId;

    public SessionResponse(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}