package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.entity.WorkerEntity;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.model.WorkerModel;
import by.nintendo.service.WorkerHoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "/worked_hourse")
public class WorkedHoursController {
    @Autowired
    private WorkerHoursService workerHoursService;
    @DeleteMapping(path = "/{id}")
    public void deleteWorkedHourseById(@PathVariable("id") Long id){

        workerHoursService.deleteById(id);

    }

    @GetMapping
    public Response getAll() {
        Response response = new Response();
        response.setEntities(workerHoursService.getAll());
        response.setStatus("OK");

        return response;
    }

    @PostMapping
    public Response create(@Valid @RequestBody WorkedHoursEntity workerEntity, BindingResult result) {
        Response response = new Response();
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getField() + " - " + fieldError.getDefaultMessage());
            }
            response.setStatus(stringBuilder.toString());

        }else{
            WorkedHoursModel worker = workerHoursService.createWorkerHours(workerEntity);
            response.setEntities(Collections.singletonList(worker));
        }

        return response;
    }
}
