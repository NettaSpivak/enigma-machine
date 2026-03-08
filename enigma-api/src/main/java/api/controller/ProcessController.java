package api.controller;

import api.manager.ProcessManager;
import api.response.ProcessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enigma/process")
public class ProcessController {
    private final ProcessManager manager;

    public ProcessController(ProcessManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<ProcessResponse> process(@RequestParam("input") String input, @RequestParam("sessionID") String sessionID) {
        ProcessResponse response = manager.processMessage(input, sessionID);
        return ResponseEntity.ok(response);
    }
}