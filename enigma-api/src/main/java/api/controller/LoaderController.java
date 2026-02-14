package api.controller;

import api.manager.EnigmaManager;
import dto.LoadMachineRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/enigma")
public class LoaderController {

    private final EnigmaManager enigmaManager;

    public LoaderController(EnigmaManager enigmaManager) {
        this.enigmaManager = enigmaManager;
    }

    @PostMapping("/load")
    public ResponseEntity<?> loadMachine(@RequestBody LoadMachineRequest request) throws Exception {
        enigmaManager.loadXml(request.getPath());
        return ResponseEntity.ok().build();
    }
}
