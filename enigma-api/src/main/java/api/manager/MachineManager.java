package api.manager;

import api.schemas.CreateSessionRequest;
import dal.dbMachine.DBMachine;
import engine.machineRepository.MachineRepository;
import loader.builder.MachineLoadResult;
import org.springframework.stereotype.Service;

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
        dbMachine.saveMachineToDB(result.getMachine(), result.getRotors(), result.getReflectors());
    }

    public MachineRepository loadMachineFromDB(CreateSessionRequest sessionRequest) throws IllegalStateException {
        return dbMachine.loadMachineFromDB(sessionRequest.getMachine());
    }
}
