package by.nintendo.controller;

import by.nintendo.entity.WorkerEntity;
import by.nintendo.mapper.WorkerMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import by.nintendo.Response;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/workers")
public class WorkersController {
    private final WorkersService workersService;
    private final WorkerMapper workerMapper;


    public WorkersController(WorkersService workersService, WorkerMapper workerMapper) {
        this.workersService = workersService;
        this.workerMapper = workerMapper;
    }


    @PostMapping
    public Response create(@Valid @RequestBody WorkerEntity workerEntity, BindingResult result) {
        Response response = new Response();
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getField() + " - " + fieldError.getDefaultMessage());
            }
            response.setErrors(stringBuilder.toString());

        }else{
            workersService.createWorker(workerEntity);
            List<WorkerModel> a = new ArrayList<>();
            a.add(workerMapper.toModel(workerEntity));
            response.setEntities(a);
        }

        return response;
    }

    @GetMapping
    public Response getAll() {
        Response response = new Response();
        response.setEntities(workersService.getAll());
        response.setErrors("OK");

        return response;
    }

    @GetMapping(path = "/{id}")
    public Response getWarkerById(@PathVariable("id") Long id){
        Response response = new Response();
        response.setErrors("ok");
        response.setEntities(Collections.singletonList(workersService.getById(id)));
        return response;
    }

    @DeleteMapping(path = "/{id}")
    public Response deleteWorkerById(@PathVariable("id") Long id){

            workersService.deleteById(id);
     return new Response();
    }


}

