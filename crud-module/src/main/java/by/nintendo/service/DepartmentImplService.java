package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.model.DepartmentModel;

import java.util.List;

public interface DepartmentImplService {

    DepartmentModel createOrUpdate(DepartmentEntity department);

    List<DepartmentModel> getAll();

    DepartmentModel getById(Long id);

    DepartmentModel deleteById(Long id);
}
