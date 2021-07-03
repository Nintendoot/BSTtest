package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.Status;
import by.nintendo.entity.DepartmentEntity;
import by.nintendo.mapper.DepartmentMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.service.DepartmentImplService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentImplService departmentImplService;
    private final DepartmentMapper departmentMapper;

    public DepartmentController(@Qualifier("departmentService") DepartmentImplService departmentImplService, DepartmentMapper departmentMapper) {
        this.departmentImplService = departmentImplService;
        this.departmentMapper = departmentMapper;
    }

    @PostMapping
    public Response<DepartmentModel> createOrUpdate(@Valid @RequestBody DepartmentEntity departmentEntity, BindingResult result) {
        log.info("POST request /department");
        Response<DepartmentModel> response = new Response<>();
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Status.NOT_CREATED.getName()).append(":");
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getField()).append("-").append(fieldError.getDefaultMessage()).append(".");
            }

            response.setStatus(stringBuilder.toString());
            response.setEntities(Collections.singletonList(departmentMapper.toModel(departmentEntity)));
            log.info("POST request hasErrors." + stringBuilder.toString());
        } else {
            DepartmentModel departmentModel = departmentImplService.createOrUpdate(departmentEntity);
            response.setStatus(Status.CREATED.getName());
            response.setEntities(Collections.singletonList(departmentModel));
        }
        return response;
    }

    @GetMapping
    public Response<DepartmentModel> getAll() {
        log.info("GET request /department");
        Response<DepartmentModel> response = new Response<>();
        response.setStatus(Status.OK.getName());
        List<DepartmentModel> list = departmentImplService.getAll();
        response.setEntities(list);
        return response;
    }
    @GetMapping(path = "/{id}")
    public Response<DepartmentModel> getWorkerById(@PathVariable("id") Long id) {
        log.info("GET request /workers/" + id);
        Response<DepartmentModel> response = new Response<>();
        response.setStatus(Status.OK.getName());
        response.setEntities(Collections.singletonList(departmentImplService.getById(id)));
        return response;
    }

    @DeleteMapping(path = "/{id}")
    public Response<DepartmentModel> deleteDepartmentById(@PathVariable("id") Long id) {
        Response<DepartmentModel> response = new Response<>();
        response.setStatus(Status.OK.getName());
        DepartmentModel departmentModel = departmentImplService.deleteById(id);
        response.setEntities(Collections.singletonList(departmentModel));
        return response;
    }

}
