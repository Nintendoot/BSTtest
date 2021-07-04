package by.nintendo.service;

import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.exception.WorkerNotFoundException;
import by.nintendo.mapper.WorkerHoursMapper;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.repository.WorkerHoursRepository;
import by.nintendo.repository.WorkersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkerHoursService implements WorkerHoursImplService {
    private final WorkerHoursMapper workerHoursMapper;
    private final WorkerHoursRepository workerHoursRepository;
    private final WorkersRepository workersRepository;

    public WorkerHoursService(WorkerHoursMapper workerHoursMapper, WorkerHoursRepository workerHoursRepository, WorkersRepository workersRepository) {
        this.workerHoursMapper = workerHoursMapper;
        this.workerHoursRepository = workerHoursRepository;
        this.workersRepository = workersRepository;
    }

    @Override
    public void createOrUpdate(WorkedHoursEntity hours) {
        log.info("Call method:createOrUpdate(WorkedHours:" + hours + ") ");
//        Optional<WorkerEntity> byId = workersRepository.findById(hours.getWorker().getId());
//        if(byId.isPresent()){
//            if(byId.get().equals(hours.getWorker())){
//                byId.get().getWorkHours().add(hours);
//                 workersRepository.save(byId.get());
//            }
//        }
        workerHoursRepository.save(hours);

    }

    @Override
    public List<WorkedHoursModel> getAll() {
        log.info("Call method WorkedHoursService: getAll().");
        return workerHoursRepository.findAll().stream().map(workerHoursMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public List<WorkedHoursModel> getById(Long id) {
        log.info("Call method WorkedHoursService: getById(Id: " + id + ") ");
        if (workersRepository.existsById(id)) {
            List<WorkedHoursEntity> time = workerHoursRepository.findAll().stream()
                    .filter(x -> x.getWorker().getId().equals(id))
                    .collect(Collectors.toList());

            return time.stream()
                    .map(workerHoursMapper::toModel)
                    .collect(Collectors.toList());

        } else {

            throw new WorkerNotFoundException("Worker with id: " + id + "not exist.");
        }
    }

    @Override
    public List<WorkedHoursModel> deleteById(Long id) {
        log.info("Call method WorkedHoursService: getById(Id: " + id + ") ");
        if (workersRepository.existsById(id)) {
            List<WorkedHoursEntity> collect = workerHoursRepository.findAll().stream()
                    .filter(x -> x.getWorker().getId().equals(id))
                    .collect(Collectors.toList());
            for (WorkedHoursEntity workedHoursEntity : collect) {
                workerHoursRepository.deleteById(workedHoursEntity.getId());
            }
            return collect.stream()
                    .map(workerHoursMapper::toModel)
                    .collect(Collectors.toList());

        } else {

            throw new WorkerNotFoundException("Worker with id: " + id + "not exist.");
        }
    }


}
