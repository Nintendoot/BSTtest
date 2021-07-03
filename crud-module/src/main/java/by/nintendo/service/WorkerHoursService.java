package by.nintendo.service;

import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.mapper.WorkerHoursMapper;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.repository.WorkerHoursRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkerHoursService implements WorkerHoursImplService {
    private final WorkerHoursMapper workerHoursMapper;
    private final WorkerHoursRepository workerHoursRepository;

    public WorkerHoursService(WorkerHoursMapper workerHoursMapper, WorkerHoursRepository workerHoursRepository) {
        this.workerHoursMapper = workerHoursMapper;
        this.workerHoursRepository = workerHoursRepository;
    }

    @Override
    public WorkedHoursModel createOrUpdate(WorkedHoursEntity hours) {
        log.info("Call method:createOrUpdate(WorkedHours:" + hours + ") ");
        workerHoursRepository.save(hours);
        return workerHoursMapper.toModel(hours);
    }

    @Override
    public List<WorkedHoursModel> getAll() {
        log.info("Call method WorkedHoursService: getAll().");
        return workerHoursRepository.findAll().stream().map(workerHoursMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<WorkedHoursModel> getById(Long id) {
        log.info("Call method WorkedHoursService: getById(Id: " + id + ") ");
        List<WorkedHoursEntity> time = workerHoursRepository.findAll().stream().filter(x -> x.getWorker().getId().equals(id)).collect(Collectors.toList());

        return time.stream().map(workerHoursMapper::toModel).collect(Collectors.toList());

    }

    @Override
    public WorkedHoursModel deleteById(Long id) {
        log.info("Call method WorkedHoursService: getById(Id: " + id + ") ");
//        Optional<WorkedHoursEntity> workedHours = workerHoursRepository.findById(id);
//        if(workedHours.isPresent()){
//
//            workerHoursRepository.deleteById(id);
//
////            WorkedHoursModel departmentModel = workerHoursMapper.toModel(workedHours.get());
////            WorkerEntity byId = workersRepository.getById(workedHours.get().getWorker().getId());
////            byId.getWorkHours().remove(workedHours.get());
////            workersRepository.save(byId);
////            workerHoursRepository.deleteById(id);
////            return departmentModel;
//        }
        return null;
    }


}
