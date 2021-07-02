package by.nintendo.service;

import by.nintendo.entity.WorkerEntity;
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
    @Autowired
    private WorkerMapper workerMapper;
    private final WorkersRepository workersRepository;

    public WorkersService(WorkersRepository workersRepository) {
        this.workersRepository = workersRepository;
    }

    public WorkerModel createWorker(WorkerEntity worker) {
        log.info("createWorker(worker:" + worker + ") ");
        workersRepository.save(worker);
        return workerMapper.toModel(worker);
    }

    public List<WorkerModel> getAll() {
        return workersRepository.findAll().stream().map(x -> workerMapper.toModel(x)).collect(Collectors.toList());
    }
    public WorkerModel getById(Long id){
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if(worker.isPresent()){
            return workerMapper.toModel(worker.get());
        }else{
            throw new NullPointerException();
        }

    }

    public WorkerModel deleteById(Long id){
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if(worker.isPresent()){
            WorkerModel workerModel = workerMapper.toModel(worker.get());
            workersRepository.delete(worker.get());
            return workerModel;
        }else{
            return null;
        }
    }
}
