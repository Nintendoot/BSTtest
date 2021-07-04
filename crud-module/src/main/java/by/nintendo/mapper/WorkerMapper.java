package by.nintendo.mapper;

import by.nintendo.entity.WorkerEntity;
import by.nintendo.model.WorkerModel;
import by.nintendo.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class WorkerMapper extends AbstractMapper<WorkerEntity, WorkerModel > {
    private final ModelMapper mapper;
    private final DepartmentRepository departmentRepository;

    public WorkerMapper(ModelMapper mapper, DepartmentRepository departmentRepository) {
        super(WorkerEntity.class, WorkerModel.class);
        this.mapper = mapper;
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(WorkerEntity.class, WorkerModel.class)
                .addMappings(m -> m.skip(WorkerModel::setDepartment)).setPostConverter(toModelConverter());
        mapper.createTypeMap(WorkerModel.class, WorkerEntity.class)
                .addMappings(m -> m.skip(WorkerEntity::setDepartment)).setPostConverter(toEntityConverter());

    }

    @Override
    public void mapSpecificFields(WorkerEntity source, WorkerModel worker) {
        worker.setDepartment(getDepartment(source));
    }

    private String getDepartment(WorkerEntity source) {
        return Objects.isNull(source) || Objects.isNull(source.getId()) ? null : source.getDepartment().getName();
    }

    @Override
    void mapSpecificFields(WorkerModel source, WorkerEntity department) {
        department.setDepartment(departmentRepository.findByName(source.getDepartment()));
    }
}
