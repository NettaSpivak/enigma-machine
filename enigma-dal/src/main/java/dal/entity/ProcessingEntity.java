package dal.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "processing")
public class ProcessingEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "machine_id", nullable = false)
    private UUID machineId;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String input;

    @Column(nullable = false)
    private String output;

    @Column(nullable = false)
    private long time;

    public ProcessingEntity() {}
}