package by.nintendo.mapper;

import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.model.WorkedHoursModel;

import by.nintendo.repository.WorkersRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Component
public class WorkerHoursMapper extends AbstractMapper<WorkedHoursModel, WorkedHoursEntity>{
private final  ModelMapper mapper;
  private final  WorkersRepository workersRepository;

    public WorkerHoursMapper(ModelMapper mapper, WorkersRepository workersRepository) {
        super(WorkedHoursEntity.class, WorkedHoursModel.class);
        this.mapper = mapper;
        this.workersRepository = workersRepository;
    }
    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(WorkedHoursEntity.class, WorkedHoursModel.class)
                .addMappings(m -> m.skip(WorkedHoursModel::setWorker)).setPostConverter(toModelConverter());
        mapper.createTypeMap(WorkedHoursModel.class, WorkedHoursEntity.class)
                .addMappings(m -> m.skip(WorkedHoursEntity::setWorker)).setPostConverter(toEntityConverter());

    }
    @Override
    public void mapSpecificFields(WorkedHoursEntity source, WorkedHoursModel worker) {
        worker.setWorker(getWarker(source));
    }

    private String getWarker(WorkedHoursEntity warker) {
        StringBuilder stringBuilder=new StringBuilder();
        if(Objects.isNull(warker) || Objects.isNull(warker.getId())){
            return null;
        }else{
          String name=  warker.getWorker().getName();
          String lastName=warker.getWorker().getLastName();
            stringBuilder.append(name).append(" ").append(lastName);
            return stringBuilder.toString();
        }
    }

    @Override
    void mapSpecificFields(WorkedHoursModel source, WorkedHoursEntity worker) {
        String[] s = source.getWorker().split(" ");
        String name=s[0];
        String lastName=s[1];
     worker.setWorker(workersRepository.findByNameAndLastName(name,lastName));
    }
}

