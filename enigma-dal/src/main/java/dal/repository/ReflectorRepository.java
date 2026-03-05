package dal.repository;

import dal.entity.MachineReflectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReflectorRepository extends JpaRepository<MachineReflectorEntity, UUID> {

    List<MachineReflectorEntity> findByMachineId(UUID machineId);

}