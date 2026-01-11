package machine.machine;

import machine.component.code.Code;
import machine.component.code.CodeSnapShot;
import machine.component.keyboard.KeyboardImpl;
import machine.component.plugboard.Plugboard;
import machine.component.rotor.Direction;
import machine.component.keyboard.Keyboard;
import machine.component.rotor.Rotor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MachineImpl implements Machine, Serializable {
    private final Keyboard keyboard;
    private Code code;

    public MachineImpl(String alphabet) {
        this.keyboard = new KeyboardImpl(alphabet);
        code = null;
    }

    @Override
    public Code getCode() {
        return this.code;
    }

    @Override
    public void setCode(Code code) {
        this.code = code;
    }

    @Override
    public void resetCode(){
        code.resetRotorsCurrentPositions();
    }

    @Override
    public char process(char _input) {
        char input = _input;
        List<Rotor> rotors = code.getRotors();
        Plugboard plugboard = code.getPlugboard();
        // plugboard processChar
        if (plugboard.isCharPlugged(input)) {
            input = plugboard.process(input);
        }
        int intermediate = keyboard.processChar(input);
        // advance rotors
        advance(rotors);
        // processChar forward
        for (Rotor rotor : rotors) {
            intermediate = rotor.process(intermediate, Direction.FORWARD);
        }
        intermediate = code.getReflector().process(intermediate);
        // processChar backward
        for (int i = rotors.size() - 1; i >= 0; i--) {
            intermediate = rotors.get(i).process(intermediate, Direction.BACKWARD);
        }
        char output = keyboard.lightALamp(intermediate);
        // plugboard processChar
        if (plugboard.isCharPlugged(output)) {
            output = plugboard.process(output);
        }
        return output;
    }

    private void advance(List<Rotor> rotors) {
        for (Rotor rotor : rotors) {
            if (!rotor.advance()) {
                break;
            }
        }
    }

    @Override
    public CodeSnapShot getCurrentCodeSnapShot() {
        if (code == null) {
            return null;
        } else {
            List<Integer> notchDistanceFromWindow = new ArrayList<>();
            for (Rotor rotor : code.getRotors()) {
                notchDistanceFromWindow.add(rotor.calculateNotchDistanceFromWindow());
            }
            return new CodeSnapShot(code.getRotorsIds(), code.getRotorsCurrentPositions(), notchDistanceFromWindow, code.getReflector().getId(), code.getPlugboardPairsList());
        }
    }
}
