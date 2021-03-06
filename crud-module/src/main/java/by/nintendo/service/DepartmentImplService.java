package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentImplService {

    void createOrUpdate(DepartmentEntity department);

    List<DepartmentEntity> getAll();

    DepartmentEntity getById(Long id);

    void deleteById(Long id);
}
