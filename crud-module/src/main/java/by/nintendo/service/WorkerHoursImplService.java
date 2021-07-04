package by.nintendo.service;

import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.model.WorkedHoursModel;


import java.util.List;

public interface WorkerHoursImplService {
    void createOrUpdate(WorkedHoursEntity workedHours);

    List<WorkedHoursEntity> getAll();

    List<WorkedHoursModel> getById(Long id);

    List<WorkedHoursModel> deleteById(Long id);
}
