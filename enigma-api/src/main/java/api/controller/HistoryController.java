package api.controller;

import api.manager.HistoryManager;
import api.response.HistoryResponse;
import api.schemas.HistoryEntry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enigma/history")
public class HistoryController {
    private final HistoryManager manager;

    public HistoryController(HistoryManager manager) {
        this.manager = manager;
    }

    @GetMapping
    public ResponseEntity<Map<String, List<HistoryEntry>>> getHistory(@RequestParam(value = "sessionID", required = false) String sessionID, @RequestParam(value = "machineName", required = false) String machineName) {
        Map<String, List<HistoryEntry>> response = manager.getHistory(sessionID, machineName);
        return ResponseEntity.ok(response);
    }
}