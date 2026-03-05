package dal.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "machines")
public class MachineEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "rotors_count", nullable = false)
    private int rotorsCount;

    @Column(name = "abc", nullable = false)
    private String abc;

    public MachineEntity() {}

    public MachineEntity(String name, int rotorsCount, String alphabet) {
        this.name = name;
        this.rotorsCount = rotorsCount;
        this.abc = alphabet;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRotorsCount() {
        return rotorsCount;
    }

    public void setRotorsCount(int rotorsCount) {
        this.rotorsCount = rotorsCount;
    }

    public String getAbc() {
        return abc;
    }

    public void setAbc(String abc) {
        this.abc = abc;
    }
}