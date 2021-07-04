package by.nintendo.service;

import by.nintendo.entity.WorkerEntity;

import java.util.List;

public interface WorkersImplService {

    void createOrUpdate(WorkerEntity worker);

    List<WorkerEntity> getAll();

    WorkerEntity getById(Long id);

    void deleteById(Long id);
}
