package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.exception.DepartmentNotFoundException;
import by.nintendo.exception.WorkerNotFoundException;
import by.nintendo.repository.DepartmentRepository;
import by.nintendo.repository.WorkersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class WorkersService implements WorkersImplService {
    private final WorkersRepository workersRepository;
@Autowired
private DepartmentRepository departmentRepository;
    public WorkersService(WorkersRepository workersRepository) {
        this.workersRepository = workersRepository;
    }

    @Override
    public void createOrUpdate(WorkerEntity worker) {
        log.info("Call method WorkersService: createOrUpdate(Worker: " + worker + ") ");
        Optional<DepartmentEntity> byId = departmentRepository.findById(worker.getDepartment().getId());
        if(byId.isPresent()){
                workersRepository.save(worker);
        }else {
            throw new DepartmentNotFoundException("Department not exist.");
        }
    }

    @Override
    public List<WorkerEntity> getAll() {
        log.info("Call method WorkersService: getAll()");
        return workersRepository.findAll();
    }

    @Override
    public WorkerEntity getById(Long id) {
        log.info("Call method WorkersService: getById(Id: " + id + ") ");
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if (worker.isPresent()) {
            return worker.get();
        } else {
            throw new WorkerNotFoundException("Worker with id: "+id+"not exist.");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Call method WorkersService: deleteById(Id: " + id + ") ");
        Optional<WorkerEntity> worker = workersRepository.findById(id);
        if (worker.isPresent()) {
           workersRepository.deleteById(id);
        } else {
            throw new WorkerNotFoundException("Worker with id: "+id+"not exist.");
        }
    }
}
