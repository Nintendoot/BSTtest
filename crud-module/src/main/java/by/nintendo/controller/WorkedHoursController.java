package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.Status;
import by.nintendo.entity.WorkedHoursEntity;
import by.nintendo.mapper.WorkerHoursMapper;
import by.nintendo.model.WorkedHoursModel;
import by.nintendo.service.WorkerHoursImplService;
import by.nintendo.util.AbstractResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/worked_hourse")
public class WorkedHoursController {
    private final WorkerHoursImplService workerHoursImplService;
    private final WorkerHoursMapper workerHoursMapper;
    private final AbstractResponse<WorkedHoursModel> response;


    public WorkedHoursController(@Qualifier("workerHoursService") WorkerHoursImplService workerHoursImplService, WorkerHoursMapper workerHoursMapper, AbstractResponse<WorkedHoursModel> response) {
        this.workerHoursImplService = workerHoursImplService;
        this.workerHoursMapper = workerHoursMapper;
        this.response = response;
    }

    @PostMapping
    public Response<?> create(@Valid @RequestBody WorkedHoursEntity workedHoursEntity) {
        log.info("POST request /worked_hours");
        WorkedHoursModel workedHoursModel1 = workerHoursMapper.toModel(workedHoursEntity);
        if (workedHoursEntity.getId() != null) {
            workerHoursImplService.createOrUpdate(workedHoursEntity);
            return response.getResponse(Status.UPDATE.getName(), Collections.singletonList(workedHoursModel1));
        } else {
            workerHoursImplService.createOrUpdate(workedHoursEntity);
            WorkedHoursModel workedHoursModel = workerHoursMapper.toModel(workedHoursEntity);
            return response.getResponse(Status.CREATED.getName(), Collections.singletonList(workedHoursModel));
        }
    }

    @DeleteMapping(path = "/{workerId}")
    public Response<?> deleteWorkedHoursByWorkerId(@PathVariable("workerId") Long id) {
        log.info("DELETE request /worked_hours/" + id);
        return response.getResponse(Status.DELETE.getName(), workerHoursImplService.deleteById(id));
    }

    @GetMapping
    public Response<?> getAll() {
        log.info("GET request /worked_hours");
        List<WorkedHoursModel> collect = workerHoursImplService.getAll().stream()
                .map(workerHoursMapper::toModel)
                .collect(Collectors.toList());
        return response.getResponse(Status.OK.getName(), collect);
    }

    @GetMapping(path = "/{workerId}")
    public Response<?> getAllWorkedHoursForWorker(@PathVariable("workerId") Long id) {
        log.info("GET request /worked_hours/" + id);
        return response.getResponse(Status.OK.getName(), workerHoursImplService.getById(id));
    }
}
