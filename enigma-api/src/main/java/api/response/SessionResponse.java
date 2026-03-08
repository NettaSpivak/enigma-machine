package api.response;

public class SessionResponse {
    private final String sessionID;

    public SessionResponse(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }
}