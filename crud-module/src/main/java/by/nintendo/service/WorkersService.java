package by.nintendo.service;

import by.nintendo.entity.WorkerEntity;
import by.nintendo.exception.WorkerNotFoundException;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.repository.WorkersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkersService implements WorkersImplService {
    private final WorkerMapper workerMapper;
    private final WorkersRepository workersRepository;

    public WorkersService(WorkersRepository workersRepository, WorkerMapper workerMapper) {
        this.workersRepository = workersRepository;
        this.workerMapper = workerMapper;
    }

    @Override
    public WorkerModel createOrUpdate(WorkerEntity worker) {
        log.info("Call method WorkersService: createOrUpdate(Worker: " + worker + ") ");
        workersRepository.save(worker);
        return workerMapper.toModel(worker);
    }

    @Override
    public List<WorkerModel> getAll() {
        log.info("Call method WorkersService: getAll()");
        return workersRepository.findAll().stream().map(workerMapper::toModel).collect(Collectors.toList());
    }

    @Override
    public WorkerModel getById(Long id) {
        log.info("Call method WorkersService: getById(Id: " + id + ") ");
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if (worker.isPresent()) {
            return workerMapper.toModel(worker.get());
        } else {
            throw new WorkerNotFoundException("Worker not found.");
        }
    }

    @Override
    public WorkerModel deleteById(Long id) {
        log.info("Call method WorkersService: deleteById(Id: " + id + ") ");
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if (worker.isPresent()) {
            WorkerModel workerModel = workerMapper.toModel(worker.get());
            workersRepository.deleteById(id);
            return workerModel;
        } else {
            throw new WorkerNotFoundException("Worker not found.");
        }
    }
}
