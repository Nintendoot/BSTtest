package by.nintendo.controller;

import by.nintendo.Status;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.model.WorkerModel;
import by.nintendo.service.WorkersImplService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import by.nintendo.Response;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/workers")
public class WorkersController {

    private final WorkersImplService workersImplService;
@Autowired
private WorkerMapper workerMapper;

    public WorkersController(@Qualifier("workersService") WorkersImplService workersImplService) {
        this.workersImplService = workersImplService;
    }

    @PostMapping
    public Response<WorkerModel> createOrUpdate(@Valid @RequestBody WorkerEntity workerEntity, BindingResult result) {
        log.info("POST request /workers");
        Response<WorkerModel> response = new Response<>();
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Status.NOT_CREATED.getName()).append(":");
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getField()).append("-").append(fieldError.getDefaultMessage()).append(".");
            }

            response.setStatus(stringBuilder.toString());
            response.setEntities(Collections.singletonList(workerMapper.toModel(workerEntity)));
            log.info("POST request hasErrors." + stringBuilder.toString());
        } else {
            WorkerModel workerModel = workersImplService.createOrUpdate(workerEntity);
            response.setStatus(Status.CREATED.getName());
            response.setEntities(Collections.singletonList(workerModel));
        }
        return response;
    }

    @GetMapping
    public Response<WorkerModel> getAll() {
        log.info("GET request /workers");
        Response<WorkerModel> response = new Response<>();
        response.setStatus(Status.OK.getName());
        List<WorkerModel> list = workersImplService.getAll();
        response.setEntities(list);
        return response;
    }

    @GetMapping(path = "/{id}")
    public Response<WorkerModel> getWorkerById(@PathVariable("id") Long id) {
        log.info("GET request /workers/" + id);
        Response<WorkerModel> response = new Response<>();
        response.setStatus(Status.OK.getName());
        response.setEntities(Collections.singletonList(workersImplService.getById(id)));
        return response;
    }

    @DeleteMapping(path = "/{id}")
    public Response<WorkerModel> deleteWorkerById(@PathVariable("id") Long id) {
        log.info("DELETE request /workers/" + id);
        WorkerModel workerModel = workersImplService.deleteById(id);
        return new Response<>(Status.DELETE.getName(), Collections.singletonList(workerModel));
    }
}

