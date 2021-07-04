package by.nintendo.controller;

import by.nintendo.Status;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.service.WorkersImplService;
import by.nintendo.util.AbstractResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import by.nintendo.Response;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/workers")
public class WorkersController {

    private final WorkersImplService workersImplService;
    private final WorkerMapper workerMapper;
    private final AbstractResponse<WorkerModel> response;

    public WorkersController(@Qualifier("workersService") WorkersImplService workersImplService, WorkerMapper workerMapper, AbstractResponse<WorkerModel> response) {
        this.workersImplService = workersImplService;
        this.workerMapper = workerMapper;
        this.response = response;
    }

    @PostMapping
    public Response<?> createOrUpdate(@Valid @RequestBody WorkerEntity workerEntity, BindingResult result) {
        log.info("POST request /workers");
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Status.NOT_CREATED.getName()).append(":");
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getField()).append("-").append(fieldError.getDefaultMessage()).append(".");
            }
            log.info("POST request hasErrors." + stringBuilder.toString());
            return response.getResponse(stringBuilder.toString(), Collections.singletonList(workerMapper.toModel(workerEntity)));
        } else {
            if (workerEntity.getId() != null) {
                workersImplService.createOrUpdate(workerEntity);
                WorkerEntity byId = workersImplService.getById(workerEntity.getId());
                return response.getResponse(Status.UPDATE.getName(), Collections.singletonList(workerMapper.toModel(byId)));
            } else {
                WorkerModel workerModel = workerMapper.toModel(workerEntity);
                workersImplService.createOrUpdate(workerEntity);
                return response.getResponse(Status.CREATED.getName(), Collections.singletonList(workerModel));
            }
        }
    }

    @GetMapping
    public Response<?> getAll() {
        log.info("GET request /workers");
        List<WorkerModel> collect = workersImplService.getAll().stream()
                .map(workerMapper::toModel)
                .collect(Collectors.toList());
        return response.getResponse(Status.OK.getName(), collect);
    }

    @GetMapping(path = "/{id}")
    public Response<?> getWorkerById(@PathVariable("id") Long id) {
        log.info("GET request /workers/" + id);
        WorkerEntity workerEntity = workersImplService.getById(id);
        return response.getResponse(Status.OK.getName(), Collections.singletonList(workerMapper.toModel(workerEntity)));
    }

    @DeleteMapping(path = "/{id}")
    public Response<?> deleteWorkerById(@PathVariable("id") Long id) {
        log.info("DELETE request /workers/" + id);
        WorkerEntity byId = workersImplService.getById(id);
        workersImplService.deleteById(id);
        return response.getResponse(Status.DELETE.getName(), Collections.singletonList(workerMapper.toModel(byId)));
    }
}

