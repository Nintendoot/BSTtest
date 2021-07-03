package by.nintendo.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.io.Serializable;

@Data
public abstract class AbstractModel implements Serializable {
    private Long id;
}
