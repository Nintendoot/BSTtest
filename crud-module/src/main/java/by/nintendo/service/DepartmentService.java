package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.DepartmentMapper;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.model.WorkerModel;
import by.nintendo.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentModel deleteById(Long id){
        Optional<DepartmentEntity> department = departmentRepository.findById(id);
           DepartmentModel departmentModel = departmentMapper.toModel(department.get());
            departmentRepository.delete(department.get());
            return departmentModel;

    }
}
