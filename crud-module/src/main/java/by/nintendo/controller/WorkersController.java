package by.nintendo.controller;

import by.nintendo.Status;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.model.WorkerModel;
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
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/workers")
public class WorkersController {

    private final WorkersImplService workersImplService;
@Autowired
private WorkerMapper workerMapper;

    @Autowired
    private AbstractResponse<WorkerModel> response;

    public WorkersController(@Qualifier("workersService") WorkersImplService workersImplService) {
        this.workersImplService = workersImplService;
    }

    @PostMapping
    public Response<?> createOrUpdate(@Valid @RequestBody WorkerEntity workerEntity, BindingResult result) {
        log.info("POST request /workers");
//        Response<WorkerModel> response = new Response<>();

        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Status.NOT_CREATED.getName()).append(":");
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getField()).append("-").append(fieldError.getDefaultMessage()).append(".");
            }
            log.info("POST request hasErrors." + stringBuilder.toString());
//            response.setStatus(stringBuilder.toString());
            WorkerModel workerModel = workerMapper.toModel(workerEntity);
//            response.setEntities(Collections.singletonList(workerMapper.toModel(workerEntity)));
         return   response.getResponse(stringBuilder.toString(),Collections.singletonList(workerModel));

        } else {
            WorkerModel list = workersImplService.createOrUpdate(workerEntity);
//            response.setStatus(Status.CREATED.getName());
//            response.setEntities(Collections.singletonList(workerModel));
          return response.getResponse(Status.CREATED.getName(),Collections.singletonList(list));
        }
    }

    @GetMapping
    public Response<?> getAll() {
        log.info("GET request /workers");
        return response.getResponse(Status.OK.getName(),workersImplService.getAll());
    }

    @GetMapping(path = "/{id}")
    public Response<?> getWorkerById(@PathVariable("id") Long id) {
        log.info("GET request /workers/" + id);
        return response.getResponse(Status.OK.getName(),Collections.singletonList(workersImplService.getById(id)));
    }

    @DeleteMapping(path = "/{id}")
    public Response<?> deleteWorkerById(@PathVariable("id") Long id) {
        log.info("DELETE request /workers/" + id);
        WorkerModel workerModel = workersImplService.deleteById(id);
        return response.getResponse(Status.DELETE.getName(),Collections.singletonList(workerModel));
    }
}

