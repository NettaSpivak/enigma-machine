package dal.dbMachine;

import dal.entity.MachineEntity;
import dal.entity.MachineReflectorEntity;
import dal.entity.MachineRotorEntity;
import engine.machineRepository.MachineRepository;
import engine.machineRepository.MachineRepositoryImpl;
import machine.component.reflector.Reflector;
import machine.component.reflector.ReflectorImpl;
import machine.component.rotor.Rotor;
import machine.component.rotor.RotorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MachineBuilderFromDB {

    public static MachineRepository buildMachineFromDB(MachineEntity machine, List<MachineRotorEntity> rotorEntities, List<MachineReflectorEntity> reflectorEntities) {
        String alphabet = machine.getAbc();
        Map<Integer, Rotor> rotors = buildRotors(rotorEntities, alphabet);
        Map<String, Reflector> reflectors = buildReflectors(reflectorEntities, alphabet);

        return new MachineRepositoryImpl(machine.getName(), alphabet, rotors, reflectors, machine.getRotorsCount());
    }

    private static Map<Integer, Rotor> buildRotors(List<MachineRotorEntity> rotorEntities, String alphabet) {
        Map<Integer, Rotor> rotors = new HashMap<>();
        for (MachineRotorEntity entity : rotorEntities) {
            Rotor rotor = new RotorImpl(entity.getRotorId(), entity.getNotch(), alphabet.length(), entity.getWiringRight().toCharArray(), entity.getWiringLeft().toCharArray());
            rotors.put(entity.getRotorId(), rotor);
        }
        return rotors;
    }

    private static Map<String, Reflector> buildReflectors(List<MachineReflectorEntity> reflectorEntities, String alphabet) {
        Map<String, Reflector> reflectors = new HashMap<>();
        for (MachineReflectorEntity entity : reflectorEntities) {
            String input = entity.getInput();
            String output = entity.getOutput();

            int[] mapping = new int[alphabet.length()];
            for (int i = 0; i < input.length(); i++) {
                int inIndex = alphabet.indexOf(input.charAt(i));
                int outIndex = alphabet.indexOf(output.charAt(i));
                mapping[inIndex] = outIndex;
                mapping[outIndex] = inIndex;
            }
            Reflector reflector = new ReflectorImpl(entity.getReflectorId().name(), mapping);
            reflectors.put(entity.getReflectorId().name(), reflector);
        }
        return reflectors;
    }
}