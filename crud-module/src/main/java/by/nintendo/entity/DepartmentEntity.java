package by.nintendo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departments")
public class DepartmentEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "departments_id")
    private Long id;
    @Column(name = "name")
    @NotEmpty(message = "The fieldname is empty.")
    @Pattern(regexp = "[A-Za-z]{4,10}", message = "name : должен быть больше 4 и меньше 10 и содержать только латинские символы.")
    private String name;
    @OneToMany(mappedBy = "department", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<WorkerEntity> workers;


}
