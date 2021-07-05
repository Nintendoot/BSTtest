package by.nintendo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkedHoursModel extends AbstractModel {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/mm/dd hh:mm:ss")
//    @JsonIgnore
    private Date startWork;
//    @JsonIgnore
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/mm/dd hh:mm:ss")
    private Date endWork;

    private String worker;
}
