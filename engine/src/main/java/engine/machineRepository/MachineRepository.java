package engine.machineRepository;

import machine.component.reflector.Reflector;
import machine.component.rotor.Rotor;

public interface MachineRepository {
    String getAlphabet();
    int getNumberOfRotorsInUse();
    Rotor getRotorById(int id);
    Reflector getReflectorById(String id);
    int getNumberOfDefinedRotors();
    int getNumberOfDefinedReflectors();
}
