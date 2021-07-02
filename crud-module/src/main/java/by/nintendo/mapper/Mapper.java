package by.nintendo.mapper;

import by.nintendo.entity.AbstractEntity;
import by.nintendo.model.AbstractModel;

public interface Mapper <M extends AbstractModel, E extends AbstractEntity>{
    E toEntity(M model);

    M toModel(E entity);
}
