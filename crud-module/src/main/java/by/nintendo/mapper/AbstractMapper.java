package by.nintendo.mapper;

import by.nintendo.entity.AbstractEntity;
import by.nintendo.model.AbstractModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper<M extends AbstractModel, E extends AbstractEntity> implements Mapper<M, E> {

    @Autowired
    ModelMapper mapper;

    private final Class<E> entityClass;
    private final Class<M> modelClass;

    AbstractMapper(Class<E> entityClass, Class<M> modelClass) {
        this.entityClass = entityClass;
        this.modelClass = modelClass;
    }

    @Override
    public E toEntity(M model) {
        return Objects.isNull(model)
                ? null
                : mapper.map(model, entityClass);
    }

    @Override
    public M toModel(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, modelClass);
    }

    Converter<E, M> toModelConverter() {
        return context -> {
            E source = context.getSource();
            M destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    Converter<M, E> toEntityConverter() {
        return context -> {
            M source = context.getSource();
            E destination = context.getDestination();
            mapSpecificFields(source, destination);
            return context.getDestination();
        };
    }

    void mapSpecificFields(E source, M destination) {
    }

    void mapSpecificFields(M source, E destination) {
    }
}
