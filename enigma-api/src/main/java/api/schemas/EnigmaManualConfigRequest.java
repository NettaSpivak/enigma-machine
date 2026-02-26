package api.schemas;

public class EnigmaManualConfigRequest extends EnigmaCodeStructureManual {
    private String sessionID;

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}

