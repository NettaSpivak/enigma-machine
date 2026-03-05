package dal.entity;

import jakarta.persistence.*;
import machine.component.reflector.ReflectorId;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "machines_reflectors")
public class MachineReflectorEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "machine_id", nullable = false)
    private UUID machineId;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "reflector_id", nullable = false, columnDefinition = "reflector_id_enum")
    private ReflectorId reflectorId;

    @Column(nullable = false)
    private String input;

    @Column(nullable = false)
    private String output;

    public MachineReflectorEntity() {}

    public UUID getId() {
        return id;
    }

    public UUID getMachineId() {
        return machineId;
    }

    public void setMachineId(UUID machineId) {
        this.machineId = machineId;
    }

    public ReflectorId getReflectorId() {
        return reflectorId;
    }

    public void setReflectorId(ReflectorId reflectorId) {
        this.reflectorId = reflectorId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}