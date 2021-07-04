package by.nintendo.service;

import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.exception.IncorrectDataException;
import by.nintendo.exception.WorkerNotFoundException;
import by.nintendo.mapper.WorkerHoursMapper;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.repository.WorkerHoursRepository;
import by.nintendo.repository.WorkersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Long id = hours.getWorker().getId();
        if (id != null) {
            if (workerHoursRepository.existsById(id)) {
                workerHoursRepository.save(hours);
            } else {
                throw new IncorrectDataException("Incorrect Data.Try again.");
            }
        } else {
            throw new WorkerNotFoundException("Worker with id: " + id + "not exist.");
        }
    }

    @Override
    public List<WorkedHoursEntity> getAll() {
        log.info("Call method WorkedHoursService: getAll().");
        return workerHoursRepository.findAll();
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
