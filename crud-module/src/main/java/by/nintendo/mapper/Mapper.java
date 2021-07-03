package by.nintendo.mapper;

import by.nintendo.entity.AbstractEntity;
import by.nintendo.model.AbstractModel;

public interface Mapper <E extends AbstractEntity, M extends AbstractModel>{
    E toEntity(M model);

    M toModel(E entity);
}
