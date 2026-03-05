package dal.repository;

import dal.entity.ProcessingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProcessingRepository extends JpaRepository<ProcessingEntity, UUID> {

    List<ProcessingEntity> findByMachineId(UUID machineId);

}