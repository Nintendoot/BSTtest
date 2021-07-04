package by.nintendo.controller;

import by.nintendo.Status;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.repository.WorkersRepository;
import by.nintendo.service.WorkersImplService;
import by.nintendo.util.AbstractResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import by.nintendo.Response;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping(path = "/workers")
public class WorkersController {

    private final WorkersImplService workersImplService;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private WorkersRepository repository;
    @Autowired
    private AbstractResponse<WorkerModel> response;

    public WorkersController(@Qualifier("workersService") WorkersImplService workersImplService) {
        this.workersImplService = workersImplService;
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

            return response.getResponse(stringBuilder.toString(), null);

        } else {
            workersImplService.createOrUpdate(workerEntity);
            return response.getResponse(Status.CREATED.getName(), null);
        }
    }

    @GetMapping
    public Response<?> getAll() {
        log.info("GET request /workers");
        return response.getResponse(Status.OK.getName(), workersImplService.getAll());
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
        WorkerModel workerModel = workersImplService.deleteById(id);
        return response.getResponse(Status.DELETE.getName(), Collections.singletonList(workerModel));
    }
}

