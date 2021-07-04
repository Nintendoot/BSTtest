package by.nintendo.service;

import by.nintendo.entity.DepartmentEntity;
import by.nintendo.exception.DepartmentAlreadyExistException;
import by.nintendo.exception.DepartmentNotFoundException;
import by.nintendo.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DepartmentService implements DepartmentImplService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void createOrUpdate(DepartmentEntity department) {
        log.info("Call method DepartmentService: createOrUpdate(Worker: " + department + ") ");
        if (!departmentRepository.existsByName(department.getName())) {
            departmentRepository.save(department);
        } else {
            throw new DepartmentAlreadyExistException("Department with name " + department.getName() + " already exist.");
        }

    }

    @Override
    public List<DepartmentEntity> getAll() {
        log.info("Call method DepartmentService: getAll()");
        return departmentRepository.findAll();
    }

    @Override
    public DepartmentEntity getById(Long id) {
        log.info("Call method DepartmentService: getById(Id: " + id + ") ");
        Optional<DepartmentEntity> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException("Department with id: " + id + " not exist.");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.info("Call method DepartmentService: deleteById(Id: " + id + ") ");
        Optional<DepartmentEntity> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
        } else {
            throw new DepartmentNotFoundException("Department with id: " + id + " not exist.");
        }
    }
}
