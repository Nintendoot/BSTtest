package by.nintendo.service;

import by.nintendo.entity.WorkerEntity;
import by.nintendo.model.WorkerModel;

import java.util.List;

public interface WorkersImplService {

    void createOrUpdate(WorkerEntity worker);

    List<WorkerModel> getAll();

    WorkerEntity getById(Long id);

    WorkerModel deleteById(Long id);
}
