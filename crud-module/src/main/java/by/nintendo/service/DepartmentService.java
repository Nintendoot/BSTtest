package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.exception.DepartmentAlreadyExistException;
import by.nintendo.exception.DepartmentNotFoundException;
import by.nintendo.mapper.DepartmentMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentService implements DepartmentImplService {
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentModel createOrUpdate(DepartmentEntity department) {
        log.info("Call method DepartmentService: createOrUpdate(Worker: " + department + ") ");
        if (!departmentRepository.existsByName(department.getName())) {
            DepartmentModel departmentModel = departmentMapper.toModel(department);
            departmentRepository.save(department);
            return departmentModel;
        } else {
            throw new DepartmentAlreadyExistException("Department with name " + department.getName() + " already exist.");
        }

    }

    @Override
    public List<DepartmentModel> getAll() {
        log.info("Call method DepartmentService: getAll()");
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentModel getById(Long id) {
        log.info("Call method DepartmentService: getById(Id: " + id + ") ");
        Optional<DepartmentEntity> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return departmentMapper.toModel(department.get());
        } else {
            throw new DepartmentNotFoundException("Department with id: "+id+" not exist.");
        }
    }

    @Override
    public DepartmentModel deleteById(Long id) {
        log.info("Call method DepartmentService: deleteById(Id: " + id + ") ");
        Optional<DepartmentEntity> department = departmentRepository.findById(id);
        if(department.isPresent()){
            DepartmentModel departmentModel = departmentMapper.toModel(department.get());
            departmentRepository.delete(department.get());
            return departmentModel;
        }else{
            throw new DepartmentNotFoundException("Department with id: "+id+" not exist.");
        }
    }
}
