package loader.builder;

import dal.entity.MachineEntity;
import dal.entity.MachineRotorEntity;
import dal.entity.MachineReflectorEntity;
import java.util.List;

public class MachineLoadResult {
    private final MachineEntity machine;
    private final List<MachineRotorEntity> rotors;
    private final List<MachineReflectorEntity> reflectors;

    public MachineLoadResult(MachineEntity machine, List<MachineRotorEntity> rotors, List<MachineReflectorEntity> reflectors) {
        this.machine = machine;
        this.rotors = rotors;
        this.reflectors = reflectors;
    }

    public MachineEntity getMachine() {
        return machine;
    }

    public List<MachineRotorEntity> getRotors() {
        return rotors;
    }

    public List<MachineReflectorEntity> getReflectors() {
        return reflectors;
    }
}