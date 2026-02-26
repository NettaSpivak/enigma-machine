package api.controller;

import api.manager.EnigmaManager;
import api.response.LoadResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/enigma/load")
public class LoaderController {
    private final EnigmaManager manager;

    public LoaderController(EnigmaManager manager) {
        this.manager = manager;
    }

    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<LoadResponse> loadMachine(@RequestParam("file") MultipartFile file) {
        try {
            String machineName = manager.loadXml(file);
            return ResponseEntity.ok(new LoadResponse(true, machineName, null));   // 200

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new LoadResponse(false, null, e.getMessage()));  // 400

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // 500
        }
    }
}
