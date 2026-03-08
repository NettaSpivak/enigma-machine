package api.controller;

import api.manager.ConfigurationManager;
import api.response.ConfigResponse;
import api.schemas.EnigmaManualConfigRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enigma/config")
public class ConfigurationController {
    private final ConfigurationManager manager;

    public ConfigurationController(ConfigurationManager manager) {
        this.manager = manager;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<ConfigResponse> getConfig(@RequestParam("sessionID") String sessionID, @RequestParam(value = "verbose", defaultValue = "false") boolean verbose) {
        ConfigResponse response = manager.getConfig(sessionID, verbose);
        return ResponseEntity.ok(response);   // 200
    }

    @PutMapping(value = "/manual", consumes = "application/json", produces = "text/plain")
    public ResponseEntity<String> setManual(@RequestBody EnigmaManualConfigRequest request) {
        manager.setCodeManual(request);
        return ResponseEntity.ok("Manual code set successfully"); // 200
    }

    @PutMapping(value = "/automatic", produces = "text/plain")
    public ResponseEntity<String> setAutomatic(@RequestParam("sessionID") String sessionID) {
        manager.setCodeAutomatic(sessionID);
        return ResponseEntity.ok("Automatic code setup completed successfully"); // 200
    }

    @PutMapping(value = "/reset", produces = "text/plain")
    public ResponseEntity<String> reset(@RequestParam("sessionID") String sessionID) {
        manager.resetCode(sessionID);
        return ResponseEntity.ok("Code reset successfully"); // 200
    }
}
