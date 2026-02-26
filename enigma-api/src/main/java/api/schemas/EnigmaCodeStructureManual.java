package api.schemas;

import dto.CodeSnapShotDto;

import java.util.ArrayList;
import java.util.List;

public class EnigmaCodeStructureManual {
    private List<RotorSelection> rotors;
    private String reflector;
    private List<PlugConnection> plugs;

    public List<RotorSelection> getRotors() {
        return rotors;
    }

    public void setRotors(List<RotorSelection> rotors) {
        this.rotors = rotors;
    }

    public String getReflector() {
        return reflector;
    }

    public void setReflector(String reflector) {
        this.reflector = reflector;
    }

    public List<PlugConnection> getPlugs() {
        return plugs;
    }

    public void setPlugs(List<PlugConnection> plugs) {
        this.plugs = plugs;
    }

    public CodeSnapShotDto toCodeSnapShotDto() {
        if (rotors == null || rotors.isEmpty()) {
            throw new IllegalArgumentException("Rotors must be provided");
        }
        if (reflector == null) {
            throw new IllegalArgumentException("Reflector must be provided");
        }
        List<Integer> rotorIds = rotors.stream().map(RotorSelection::getRotorNumber).toList();
        List<Character> rotorPositions = rotors.stream().map(r -> r.getRotorPosition().charAt(0)).toList();
        List<char[]> plugboard = new ArrayList<>();
        if (plugs != null) {
            for (PlugConnection plug : plugs) {
                plugboard.add(new char[]{plug.getPlug1().charAt(0), plug.getPlug2().charAt(0)});
            }
        }
        return new CodeSnapShotDto(rotorIds, rotorPositions, null, reflector, plugboard);
    }
}
