package by.nintendo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "workers")
public class WorkerEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worker_id")
    private Long id;
    @Column(name = "name")
    @NotNull
    @Max(value = 5, message = "!!!!!!!!")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "position")
    private String position;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "worker")
    private List<WorkedHoursEntity> workHours;
    @ManyToOne
    @JoinColumn(name = "departments_id")
    private DepartmentEntity department;

    @Override
    public String toString() {
        return "WorkerEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", workHours=" + workHours +
                ", department=" + department +
                '}';
    }
}
