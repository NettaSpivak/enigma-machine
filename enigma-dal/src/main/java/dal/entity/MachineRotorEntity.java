package dal.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "machines_rotors")
public class MachineRotorEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "machine_id", nullable = false)
    private UUID machineId;

    @Column(name = "rotor_id", nullable = false)
    private int rotorId;

    private Integer notch;

    @Column(name = "wiring_right", nullable = false)
    private String wiringRight;

    @Column(name = "wiring_left", nullable = false)
    private String wiringLeft;

    public MachineRotorEntity() {}

    public UUID getId() {
        return id;
    }

    public UUID getMachineId() {
        return machineId;
    }

    public void setMachineId(UUID machineId) {
        this.machineId = machineId;
    }

    public int getRotorId() {
        return rotorId;
    }

    public void setRotorId(int rotorId) {
        this.rotorId = rotorId;
    }

    public Integer getNotch() {
        return notch;
    }

    public void setNotch(Integer notch) {
        this.notch = notch;
    }

    public String getWiringRight() {
        return wiringRight;
    }

    public void setWiringRight(String wiringRight) {
        this.wiringRight = wiringRight;
    }

    public String getWiringLeft() {
        return wiringLeft;
    }

    public void setWiringLeft(String wiringLeft) {
        this.wiringLeft = wiringLeft;
    }
}