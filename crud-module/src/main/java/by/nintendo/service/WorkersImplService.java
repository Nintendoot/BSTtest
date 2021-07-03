package by.nintendo.service;

import by.nintendo.entity.WorkerEntity;
import by.nintendo.exception.WorkerNotFoundException;
import by.nintendo.model.WorkerModel;

import java.util.List;

public interface WorkersImplService {

    WorkerModel createOrUpdate(WorkerEntity worker);

    List<WorkerModel> getAll();

    WorkerModel getById(Long id);

    WorkerModel deleteById(Long id);
}
