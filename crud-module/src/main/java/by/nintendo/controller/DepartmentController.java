package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.Status;
import by.nintendo.entity.DepartmentEntity;
import by.nintendo.mapper.DepartmentMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.repository.DepartmentRepository;
import by.nintendo.service.DepartmentImplService;
import by.nintendo.util.AbstractResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/department")
public class DepartmentController {
    private final AbstractResponse<DepartmentModel> response;
    private final DepartmentImplService departmentImplService;
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository repository;

    public DepartmentController(@Qualifier("departmentService") DepartmentImplService departmentImplService, DepartmentMapper departmentMapper, AbstractResponse<DepartmentModel> response, DepartmentRepository repository) {
        this.departmentImplService = departmentImplService;
        this.departmentMapper = departmentMapper;
        this.response = response;
        this.repository = repository;
    }

    @PostMapping
    public Response<?> createOrUpdate(@Valid @RequestBody DepartmentEntity departmentEntity, BindingResult result) {
        log.info("POST request /department");
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Status.NOT_CREATED.getName()).append(":");
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getField()).append("-").append(fieldError.getDefaultMessage()).append(".");
            }
            log.info("POST request hasErrors." + stringBuilder.toString());
            return response.getResponse(stringBuilder.toString(), Collections.singletonList(departmentMapper.toModel(departmentEntity)));

        } else {
            if (departmentEntity.getId() != null) {
                departmentImplService.createOrUpdate(departmentEntity);
                DepartmentEntity one = departmentImplService.getById(departmentEntity.getId());
                return response.getResponse(Status.UPDATE.getName(), Collections.singletonList(departmentMapper.toModel(one)));

            }
            departmentImplService.createOrUpdate(departmentEntity);
            DepartmentEntity byId = repository.findByName(departmentEntity.getName());
            return response.getResponse(Status.CREATED.getName(), Collections.singletonList(departmentMapper.toModel(byId)));
        }
    }

    @GetMapping
    public Response<?> getAll() {
        log.info("GET request /department");
        List<DepartmentModel> collect = departmentImplService.getAll().stream().map(departmentMapper::toModel).collect(Collectors.toList());
        return response.getResponse(Status.OK.getName(), collect);
    }


    @GetMapping(path = "/{id}")
    public Response<?> getWorkerById(@PathVariable("id") Long id) {
        log.info("GET request /department/" + id);
        DepartmentEntity byId = departmentImplService.getById(id);
        return response.getResponse(Status.OK.getName(), Collections.singletonList(departmentMapper.toModel(byId)));
    }

    @DeleteMapping(path = "/{id}")
    public Response<?> deleteDepartmentById(@PathVariable("id") Long id) {
        log.info("DELETE request /department/" + id);
        DepartmentEntity byId = departmentImplService.getById(id);
        departmentImplService.deleteById(id);
        return response.getResponse(Status.DELETE.getName(), Collections.singletonList(departmentMapper.toModel(byId)));
    }

}
