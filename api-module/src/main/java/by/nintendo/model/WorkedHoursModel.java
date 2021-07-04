package by.nintendo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkedHoursModel extends AbstractModel {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/mm/dd hh:mm:ss")
    private LocalDateTime startWork;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/mm/dd hh:mm:ss")
    private LocalDateTime endWork;

    private String worker;
}
