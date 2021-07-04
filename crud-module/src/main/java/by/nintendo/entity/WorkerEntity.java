package by.nintendo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
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
    @Pattern(regexp = "[A-Za-z]{2,10}", message = "name : должен быть больше 2 и меньше 10 и содержать только латинские символы.")
    private String name;

    @Column(name = "last_name")
    @Pattern(regexp = "[A-Za-z]{4,10}", message = "lastName : должен быть больше 4 и меньше 10 и содержать только латинские символы.")
    private String lastName;

    @Column(name = "position")
    @Pattern(regexp = "[A-Za-z]{4,10}", message = "position : должен быть больше 4 и меньше 10 и содержать только латинские символы.")
    private String position;

    @OneToMany(mappedBy = "worker",cascade = CascadeType.REMOVE)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<WorkedHoursEntity> workHours;
    @ManyToOne
    @JoinTable(name = "warkers_department",
            joinColumns = @JoinColumn(name = "worker_id"),
            inverseJoinColumns = @JoinColumn(name = "departments_id"))
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
