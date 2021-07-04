package by.nintendo.model;

import lombok.*;

import java.io.Serializable;

@Data
public abstract class AbstractModel implements Serializable {
    private Long id;
}
