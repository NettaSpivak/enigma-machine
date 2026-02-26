package api.schemas;

import dto.CodeSnapShotDto;
import java.util.List;

public class EnigmaCodeStructure {
    private List<RotorSelectionWithNotch> rotors;
    private String reflector;
    private List<PlugConnection> plugs;

    public EnigmaCodeStructure() {}

    public EnigmaCodeStructure(List<RotorSelectionWithNotch> rotors, String reflector, List<PlugConnection> plugs) {
        this.rotors = rotors;
        this.reflector = reflector;
        this.plugs = plugs;
    }

    public static EnigmaCodeStructure fromCodeSnapShotDto(CodeSnapShotDto code) {
        if (code == null) {
            return null;
        }
        List<RotorSelectionWithNotch> rotors = code.getRotorIds().stream()
                .map(rotorId -> {
                    int index = code.getRotorIds().indexOf(rotorId);
                    return new RotorSelectionWithNotch(rotorId, String.valueOf(code.getRotorPositions().get(index)), code.getNotchDistanceFromWindow().get(index));
                }).toList();
        List<PlugConnection> plugs = code.getPlugboard().stream()
                .map(connection -> new PlugConnection(String.valueOf(connection[0]), String.valueOf(connection[1])))
                .toList();
        return new EnigmaCodeStructure(rotors, code.getReflectorId(), plugs);
    }

    public List<RotorSelectionWithNotch> getRotors() {
        return rotors;
    }

    public String getReflector() {
        return reflector;
    }

    public List<PlugConnection> getPlugs() {
        return plugs;
    }

    public void setRotors(List<RotorSelectionWithNotch> rotors) {
        this.rotors = rotors;
    }

    public void setReflector(String reflector) {
        this.reflector = reflector;
    }

    public void setPlugs(List<PlugConnection> plugs) {
        this.plugs = plugs;
    }
}
