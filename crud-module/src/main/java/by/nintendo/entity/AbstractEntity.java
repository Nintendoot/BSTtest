package by.nintendo.entity;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public abstract class AbstractEntity implements Serializable {
}
