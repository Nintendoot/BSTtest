package by.nintendo.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.io.Serializable;
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = DepartmentModel.class),
//        @JsonSubTypes.Type(value = WorkedHoursModel.class),
//        @JsonSubTypes.Type(value = WorkerModel.class)
//})
@Data
public abstract class AbstractModel implements Serializable {
    private Long id;
}
