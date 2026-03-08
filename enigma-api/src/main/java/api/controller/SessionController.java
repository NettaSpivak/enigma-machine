package api.controller;

import api.manager.SessionManager;
import api.response.SessionResponse;
import api.schemas.CreateSessionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/enigma/session")
public class SessionController {
    private final SessionManager sessionManager;

    public SessionController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @PostMapping
    public ResponseEntity<SessionResponse> createSession(@RequestBody CreateSessionRequest request) {
        String sessionID = sessionManager.createSession(request);
        return ResponseEntity.ok(new SessionResponse(sessionID)); // 200
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSession(@RequestParam("sessionID") String sessionID) {
        sessionManager.deleteSession(sessionID);
        return ResponseEntity.noContent().build(); // 204
    }
}