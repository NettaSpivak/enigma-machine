package api.manager;

import api.response.ConfigResponse;
import api.schemas.EnigmaManualConfigRequest;
import dto.CodeSnapShotDto;
import dto.MachineStatusDto;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

@Service
public class ConfigurationManager {
    private final SessionRegistry sessionRegistry;

    public ConfigurationManager(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public ConfigResponse getConfig(String sessionID, boolean verbose) {
        Session session = sessionRegistry.getSession(sessionID);
        MachineStatusDto machineStatus = session.getEngine().getMachineStatus();
        return ConfigResponse.fromMachineStatusDto(machineStatus, verbose);
    }

    public void setCodeManual(EnigmaManualConfigRequest request) throws IllegalArgumentException {
        CodeSnapShotDto codeSnapShotDto = request.toCodeSnapShotDto();
        String sessionID = request.getSessionID();
        Session session = sessionRegistry.getSession(sessionID);
        session.getEngine().codeManual(request.getSessionID(), codeSnapShotDto);
    }

    public void setCodeAutomatic(String sessionID) throws IllegalArgumentException {
        Session session = sessionRegistry.getSession(sessionID);
        session.getEngine().codeAutomatic(sessionID);
    }

    public void resetCode(String sessionID) throws IllegalArgumentException {
        Session session = sessionRegistry.getSession(sessionID);
        session.getEngine().resetCode(sessionID);
    }
}
