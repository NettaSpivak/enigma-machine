package dal.repository;

import dal.entity.MachineRotorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RotorRepository extends JpaRepository<MachineRotorEntity, UUID> {

    List<MachineRotorEntity> findByMachineId(UUID machineId);

}