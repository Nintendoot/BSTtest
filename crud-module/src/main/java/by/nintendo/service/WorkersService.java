package by.nintendo.service;

import by.nintendo.entity.WorkerEntity;
import by.nintendo.exception.WorkerNotFoundException;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.repository.WorkersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WorkersService {
    private final WorkerMapper workerMapper;
    private final WorkersRepository workersRepository;

    public WorkersService(WorkersRepository workersRepository, WorkerMapper workerMapper) {
        this.workersRepository = workersRepository;
        this.workerMapper = workerMapper;
    }

    public WorkerModel createWorker(WorkerEntity worker) {
        log.info("Call method: createWorker(Worker: " + worker + ") ");
        workersRepository.save(worker);
        return workerMapper.toModel(worker);
    }

    public List<WorkerModel> getAll() {
        return workersRepository.findAll().stream().map(workerMapper::toModel).collect(Collectors.toList());
    }
    public WorkerEntity getById(Long id){
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if(worker.isPresent()){
            return worker.get();
        }else{
            throw new WorkerNotFoundException("Worker not found.");
        }

    }

    public WorkerModel deleteById(Long id){
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if(worker.isPresent()){
            WorkerModel workerModel = workerMapper.toModel(worker.get());
            workersRepository.deleteById(id);
            return workerModel;
        }else{
            throw new WorkerNotFoundException("Worker not found.");
        }
    }
}
