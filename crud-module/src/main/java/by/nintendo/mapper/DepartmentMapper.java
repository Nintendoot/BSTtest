package by.nintendo.mapper;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.model.DepartmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper extends AbstractMapper<DepartmentEntity, DepartmentModel> {
    @Autowired
    public DepartmentMapper() {
        super(DepartmentEntity.class, DepartmentModel.class);
    }
}
