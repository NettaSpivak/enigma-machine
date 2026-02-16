package api.controller;

import api.manager.EnigmaManager;
import api.response.config.ConfigResponse;
import api.response.error.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enigma/config")
public class ConfigurationController {
    private final EnigmaManager manager;

    public ConfigurationController(EnigmaManager manager) {
        this.manager = manager;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getConfig(@RequestParam("sessionID") String sessionID, @RequestParam(value = "verbose", defaultValue = "false") boolean verbose) {
        try {
            // כרגע sessionID לא בשימוש — נשנה כשנוסיף sessions
            ConfigResponse response = manager.getConfig(sessionID, verbose);
            return ResponseEntity.ok(response);   // 200

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));   // 400

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }

    @PostMapping("/manual")
    public void setManual(@RequestBody CodeSnapShotDto dto) {
        manager.setCodeManual(dto);
    }

    @PostMapping("/auto")
    public void setAutomatic() {
        manager.setCodeAutomatic();
    }

    @PostMapping("/reset")
    public void reset() {
        manager.resetCode();
    }


}
