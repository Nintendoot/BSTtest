package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerHoursMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.model.WorkerModel;
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
public class WorkerHoursService {
@Autowired
private WorkerHoursMapper workerHoursMapper;

@Autowired
private WorkerHoursRepository workerHoursRepository;

@Autowired
private WorkersRepository workersRepository;
    public void deleteById(Long id){
        Optional<WorkedHoursEntity> workedHours = workerHoursRepository.findById(id);
        if(workedHours.isPresent()){

            workerHoursRepository.deleteById(id);
//            WorkedHoursModel departmentModel = workerHoursMapper.toModel(workedHours.get());
//            WorkerEntity byId = workersRepository.getById(workedHours.get().getWorker().getId());
//            byId.getWorkHours().remove(workedHours.get());
//            workersRepository.save(byId);
//            workerHoursRepository.deleteById(id);
//            return departmentModel;
        }
    }

    public List<WorkedHoursModel> getAll() {
        return workerHoursRepository.findAll().stream().map(x -> workerHoursMapper.toModel(x)).collect(Collectors.toList());
    }

    public WorkedHoursModel createWorkerHours(WorkedHoursEntity hours) {
        log.info("createWorker(worker:" + hours + ") ");
        workerHoursRepository.save(hours);
        return workerHoursMapper.toModel(hours);
    }
}
