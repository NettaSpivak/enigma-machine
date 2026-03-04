package dal.registry;

import engine.machineRepository.MachineRepository;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MachineRegistry {
    private final Map<String, MachineRepository> machines = new ConcurrentHashMap<>();

    public void addMachine(MachineRepository machine) throws IllegalArgumentException {
        String machineName = machine.getMachineName();
        if (machines.containsKey(machineName)) {
            throw new IllegalArgumentException("Machine with name '" + machineName + "' already exists");
        }
        machines.put(machineName, machine);
    }

    public MachineRepository getMachine(String machineName) throws IllegalArgumentException {
        MachineRepository repository = machines.get(machineName);
        if (repository == null) {
            throw new IllegalArgumentException("Unknown machine name: " + machineName);
        }
        return repository;
    }

    public boolean machineExists(String machineName) {
        return machines.containsKey(machineName);
    }
}