package by.nintendo.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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
    @NotEmpty(message = "The field startWork is empty.")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Pattern(regexp = "(\\d{4})((0[1-9])|(1[0-2]))(0[1-9]|[12][0-9]|3[01])(?:[0-1]?[0-9]|[2][1-4]):[0-5]?[0-9]:[0-5]?[0-9]", message = "Bad format.Good format yyyy/mm/dd hh:mm:ss")
    private LocalDateTime startWork;

    @NotEmpty(message = "The field endWork is empty.")
    @Column(name = "end_work")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Pattern(regexp = "(\\d{4})((0[1-9])|(1[0-2]))(0[1-9]|[12][0-9]|3[01])(?:[0-1]?[0-9]|[2][1-4]):[0-5]?[0-9]:[0-5]?[0-9]", message = "Bad format.Good format yyyy/mm/dd hh:mm:ss")
    private LocalDateTime endWork;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private WorkerEntity worker;
}