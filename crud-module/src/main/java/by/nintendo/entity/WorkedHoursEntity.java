package by.nintendo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "worked_hours")
public class WorkedHoursEntity extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worked_hours_id")
    private Long id;
    @Column(name = "start_work")
    private LocalDateTime startWork;
    @Column(name = "end_work")
    private LocalDateTime endWork;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private WorkerEntity worker;
}
