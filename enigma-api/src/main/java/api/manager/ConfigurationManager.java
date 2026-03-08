package api.manager;

import api.response.ConfigResponse;
import api.schemas.EnigmaManualConfigRequest;
import dto.CodeSnapShotDto;
import dto.MachineStatusDto;
import org.springframework.stereotype.Service;
import session.Session;
import session.SessionRegistry;

import java.util.NoSuchElementException;

@Service
public class ConfigurationManager {
    private final SessionRegistry sessionRegistry;

    public ConfigurationManager(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public ConfigResponse getConfig(String sessionID, boolean verbose) throws NoSuchElementException {
        Session session = sessionRegistry.getSession(sessionID);
        MachineStatusDto machineStatus = session.getEngine().getMachineStatus();
        return ConfigResponse.fromMachineStatusDto(machineStatus, verbose);
    }

    public void setCodeManual(EnigmaManualConfigRequest request) throws NoSuchElementException, IllegalArgumentException {
        CodeSnapShotDto codeSnapShotDto = request.toCodeSnapShotDto();
        String sessionID = request.getSessionID();
        Session session = sessionRegistry.getSession(sessionID);
        session.getEngine().codeManual(codeSnapShotDto);
    }

    public void setCodeAutomatic(String sessionID) throws NoSuchElementException {
        Session session = sessionRegistry.getSession(sessionID);
        session.getEngine().codeAutomatic();
    }

    public void resetCode(String sessionID) throws NoSuchElementException, IllegalStateException {
        Session session = sessionRegistry.getSession(sessionID);
        session.getEngine().resetCode();
    }
}
