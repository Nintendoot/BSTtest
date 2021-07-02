package by.nintendo.service;

import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.repository.WorkersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

        return workerMapper.toModel(workersRepository.save(worker));
    }

    public List<WorkerModel> getAll() {

        return workersRepository.findAll().stream().map(x -> workerMapper.toModel(x)).collect(Collectors.toList());
    }
}
