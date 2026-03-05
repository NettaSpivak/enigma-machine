package dal.dbMachine;

import dal.entity.*;
import dal.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.UUID;

@Component
public class DBMachine {
    private final MachineRepository machineRepository;
    private final RotorRepository rotorRepository;
    private final ReflectorRepository reflectorRepository;

    public DBMachine(MachineRepository machineRepository, RotorRepository rotorRepository, ReflectorRepository reflectorRepository) {
        this.machineRepository = machineRepository;
        this.rotorRepository = rotorRepository;
        this.reflectorRepository = reflectorRepository;
    }

    @Transactional
    public void saveMachineWithComponents(MachineEntity machine, List<MachineRotorEntity> rotors, List<MachineReflectorEntity> reflectors) {
        MachineEntity savedMachine = machineRepository.save(machine);
        UUID machineId = savedMachine.getId();
        rotors.forEach(r -> r.setMachineId(machineId));
        reflectors.forEach(r -> r.setMachineId(machineId));
        rotorRepository.saveAll(rotors);
        reflectorRepository.saveAll(reflectors);
    }

    public MachineEntity getMachine(String name) throws IllegalArgumentException {
        return machineRepository.findByName(name).orElseThrow(() -> new IllegalArgumentException("Unknown machine name: " + name));
    }

    public List<MachineRotorEntity> getRotors(UUID machineId) {
        return rotorRepository.findByMachineId(machineId);
    }

    public List<MachineReflectorEntity> getReflectors(UUID machineId) {
        return reflectorRepository.findByMachineId(machineId);
    }

    public boolean machineExists(String name) {
        return machineRepository.existsByName(name);
    }
}