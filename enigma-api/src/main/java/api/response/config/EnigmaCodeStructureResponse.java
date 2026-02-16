package api.response.config;

import dto.CodeSnapShotDto;
import java.util.List;

public class EnigmaCodeStructureResponse {
    private List<RotorSelectionWithNotchResponse> rotors;
    private String reflector;
    private List<PlugConnectionResponse> plugs;

    public EnigmaCodeStructureResponse(List<RotorSelectionWithNotchResponse> rotors, String reflector, List<PlugConnectionResponse> plugs) {
        this.rotors = rotors;
        this.reflector = reflector;
        this.plugs = plugs;
    }

    public static EnigmaCodeStructureResponse fromCodeSnapShotDto(CodeSnapShotDto code) {
        if (code == null) {
            return null;
        }
        List<RotorSelectionWithNotchResponse> rotors = code.getRotorIds().stream()
                .map(rotorId -> {
                    int index = code.getRotorIds().indexOf(rotorId);
                    return new RotorSelectionWithNotchResponse(rotorId, String.valueOf(code.getRotorPositions().get(index)), code.getNotchDistanceFromWindow().get(index));
                }).toList();
        List<PlugConnectionResponse> plugs = code.getPlugboard().stream()
                .map(connection -> new PlugConnectionResponse(String.valueOf(connection[0]), String.valueOf(connection[1])))
                .toList();
        return new EnigmaCodeStructureResponse(rotors, code.getReflectorId(), plugs);
    }

    public List<RotorSelectionWithNotchResponse> getRotors() {
        return rotors;
    }

    public String getReflector() {
        return reflector;
    }

    public List<PlugConnectionResponse> getPlugs() {
        return plugs;
    }
}
