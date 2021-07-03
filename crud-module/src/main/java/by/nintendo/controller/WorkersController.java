package by.nintendo.controller;

import by.nintendo.Status;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.repository.WorkersRepository;
import by.nintendo.service.WorkersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import by.nintendo.Response;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Slf4j
@RestController
@RequestMapping(path = "/workers")
public class WorkersController {
    private final WorkersService workersService;
    private final WorkerMapper workerMapper;
@Autowired
private WorkersRepository repository;

    public WorkersController(WorkersService workersService, WorkerMapper workerMapper) {
        this.workersService = workersService;
        this.workerMapper = workerMapper;
    }


    @PostMapping
    public Response<WorkerModel> create(@Valid @RequestBody WorkerEntity workerEntity, BindingResult result) {
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
            log.info("POST request hasErrors."+stringBuilder.toString());
        } else {
            WorkerModel worker = workersService.createWorker(workerEntity);
            response.setStatus(Status.CREATED.getName());
            response.setEntities(Collections.singletonList(worker));
        }
        return response;
    }

    @GetMapping
    public Response<WorkerModel> getAll() {
        log.info("GET request /workers");
        Response<WorkerModel> response = new Response<>();
        if(workersService.getAll().isEmpty()){
            log.info("GET request hasErrors.");
            response.setStatus(Status.BAD.getName());
        }
        response.setStatus(Status.OK.getName());
        List<WorkerModel> list=workersService.getAll();
        response.setEntities(list);
        return response;
    }

    @GetMapping(path = "/{id}")
    public Response<WorkerModel> getWorkerById(@PathVariable("id") Long id) {
        Response<WorkerModel> response = new Response<>();

        response.setStatus(Status.OK.getName());
        WorkerModel workerModel = workerMapper.toModel(workersService.getById(id));
        response.setEntities(Collections.singletonList(workerModel));

        return response;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWorkerById(@PathVariable("id") Long id) {
      workersService.deleteById(id);
//        return new Response<>(Status.DELETE.getName(), Collections.singletonList(worker) );
    }


}

