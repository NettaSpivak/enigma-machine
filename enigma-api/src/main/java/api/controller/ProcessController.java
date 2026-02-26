package api.controller;

import api.manager.EnigmaManager;
import api.response.ProcessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enigma/process")
public class ProcessController {
    private final EnigmaManager manager;

    public ProcessController(EnigmaManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<?> process(@RequestParam("input") String input, @RequestParam("sessionId") String sessionId) {
        try {
            ProcessResponse response = manager.processMessage(input, sessionId);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // 400
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body(e.getMessage()); // 409
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Internal server error"); // 500
        }
    }
}