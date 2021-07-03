package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.Status;
import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.mapper.WorkerHoursMapper;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.service.WorkerHoursImplService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@Slf4j
@RestController
@RequestMapping(path = "/worked_hourse")
public class WorkedHoursController {
    private final WorkerHoursImplService workerHoursImplService;
    private final WorkerHoursMapper workerHoursMapper;

    public WorkedHoursController(@Qualifier("workerHoursService") WorkerHoursImplService workerHoursImplService, WorkerHoursMapper workerHoursMapper) {
        this.workerHoursImplService = workerHoursImplService;
        this.workerHoursMapper = workerHoursMapper;
    }

    @PostMapping
    public void create(@Valid @RequestBody WorkedHoursEntity workedHoursEntity, BindingResult result) {
        log.info("POST request /worked_hourse");
        Response<WorkedHoursModel> response = new Response<>();
        if (result.hasErrors()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Status.NOT_CREATED.getName()).append(":");
            for (FieldError fieldError : result.getFieldErrors()) {
                stringBuilder.append(fieldError.getDefaultMessage());
            }

            response.setStatus(stringBuilder.toString());
            response.setEntities(Collections.singletonList(workerHoursMapper.toModel(workedHoursEntity)));
            log.info("POST request hasErrors." + stringBuilder.toString());
        } else {
//            WorkedHoursModel workedHoursModel = workerHoursService.createWorkerHours(workedHoursEntity);
            response.setStatus(Status.CREATED.getName());
            workerHoursImplService.createOrUpdate(workedHoursEntity);
//            response.setEntities(Collections.singletonList(workerHoursService.createWorkerHours(workedHoursEntity)));
        }
//        return response;
    }


    @DeleteMapping(path = "/{id}")
    public Response<WorkedHoursModel> deleteWorkedHourseById(@PathVariable("id") Long id) {
        Response<WorkedHoursModel> response = new Response<>();
//        response.setEntities(workerHoursImplService.deleteById(id));
        response.setStatus(Status.OK.getName());
        return response;
    }

    @GetMapping
    public Response<WorkedHoursModel> getAll() {
        Response<WorkedHoursModel> response = new Response<>();
        response.setEntities(workerHoursImplService.getAll());
        response.setStatus(Status.OK.getName());
        return response;
    }

    @GetMapping(path = "/{workerId}")
    public Response<WorkedHoursModel> getAllWorkedHoursForWorker(@PathVariable("workerId") Long id) {
        Response<WorkedHoursModel> response = new Response<>();
//        List<WorkedHoursModel> list= workerHoursImplService.getById(id);
        response.setEntities(workerHoursImplService.getById(id));
        response.setStatus(Status.OK.getName());
        return response;
    }

//    @PostMapping
//    public Response create(@Valid @RequestBody WorkedHoursEntity workerEntity, BindingResult result) {
//        Response response = new Response();
//        if (result.hasErrors()) {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (FieldError fieldError : result.getFieldErrors()) {
//                stringBuilder.append(fieldError.getField() + " - " + fieldError.getDefaultMessage());
//            }
//            response.setStatus(stringBuilder.toString());
//
//        }else{
//            WorkedHoursModel worker = workerHoursService.createWorkerHours(workerEntity);
//            response.setEntities(Collections.singletonList(worker));
//        }
//
//        return response;
//    }
}
