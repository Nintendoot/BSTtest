package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.Status;
import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.mapper.WorkerHoursMapper;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.service.WorkerHoursImplService;
import by.nintendo.util.AbstractResponse;
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
    private final AbstractResponse<WorkedHoursModel> response;

    public WorkedHoursController(@Qualifier("workerHoursService") WorkerHoursImplService workerHoursImplService, WorkerHoursMapper workerHoursMapper, AbstractResponse response) {
        this.workerHoursImplService = workerHoursImplService;
        this.workerHoursMapper = workerHoursMapper;
        this.response = response;
    }

    @PostMapping
    public void create(@Valid @RequestBody WorkedHoursEntity workedHoursEntity, BindingResult result) {
        log.info("POST request /worked_hours");
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

    @DeleteMapping(path = "/{workerId}")
    public Response<?> deleteWorkedHoursByWorkerId(@PathVariable("workerId") Long id) {
        return  response.getResponse(Status.DELETE.getName(), workerHoursImplService.deleteById(id));

    }

    @GetMapping
    public Response<?> getAll() {
        return response.getResponse(Status.OK.getName(), workerHoursImplService.getAll());
    }

    @GetMapping(path = "/{workerId}")
    public Response<?> getAllWorkedHoursForWorker(@PathVariable("workerId") Long id) {
         return response.getResponse(Status.OK.getName(), workerHoursImplService.getById(id));
    }
}
