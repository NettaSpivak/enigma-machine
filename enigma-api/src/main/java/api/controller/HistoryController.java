package api.controller;

import api.manager.HistoryManager;
import api.response.HistoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enigma/history")
public class HistoryController {
    private final HistoryManager manager;

    public HistoryController(HistoryManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public ResponseEntity<?> getHistory(@RequestParam(value = "sessionID", required = false) String sessionID, @RequestParam(value = "machineName", required = false) String machineName) {
        try {
            HistoryResponse response = manager.getHistory(sessionID, machineName);
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