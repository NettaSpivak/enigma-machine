package api.manager;

import api.schemas.CreateSessionRequest;
import dal.builder.MachineBuilderFromDB;
import dal.dbMachine.DBMachine;
import dal.entity.MachineEntity;
import dal.entity.MachineReflectorEntity;
import dal.entity.MachineRotorEntity;
import engine.machineRepository.MachineRepository;
import loader.builder.MachineLoadResult;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MachineManager {
    private final DBMachine dbMachine;

    public MachineManager(DBMachine dbMachine) {
        this.dbMachine = dbMachine;
    }

    public void saveMachineToDB(MachineLoadResult result) throws IllegalArgumentException {
        if (dbMachine.machineExists(result.getMachine().getName())) {
            throw new IllegalArgumentException("Failed to save machine: machine with the same name already exists");
        }
        dbMachine.saveMachineWithComponents(result.getMachine(), result.getRotors(), result.getReflectors());
    }

    public MachineRepository loadMachineFromDB(CreateSessionRequest sessionRequest) {
        MachineEntity machine = dbMachine.getMachine(sessionRequest.getMachine());
        List<MachineRotorEntity> rotors = dbMachine.getRotors(machine.getId());
        List<MachineReflectorEntity> reflectors = dbMachine.getReflectors(machine.getId());
        return MachineBuilderFromDB.build(machine, rotors, reflectors);
    }
}