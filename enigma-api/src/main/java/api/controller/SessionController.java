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
    public ResponseEntity<?> createSession(@RequestBody CreateSessionRequest request) {
        try {
            String sessionID = sessionManager.createSession(request);
            return ResponseEntity.ok(new SessionResponse(sessionID)); // 200

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body(e.getMessage()); // 409
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error"); // 500
        }
    }

    @DeleteMapping
    public ResponseEntity<?> deleteSession(@RequestParam("sessionID") String sessionID) {
        try {
            sessionManager.deleteSession(sessionID);
            return ResponseEntity.noContent().build(); // 204

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(Map.of("error", e.getMessage())); // 404
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error"); // 500
        }
    }
}