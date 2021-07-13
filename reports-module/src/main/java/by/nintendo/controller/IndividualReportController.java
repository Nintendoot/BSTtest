package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.Status;
import by.nintendo.mapper.WorkerModelMapper;
import by.nintendo.model.DepartmentModel;
import by.nintendo.model.WorkerModel;
import by.nintendo.model.WorkerReportModel;
import by.nintendo.util.ModelReportSerializ;
import by.nintendo.util.ReportResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestControllerAdvice
@RequestMapping(path = "/report")
public class IndividualReportController {
    private final String url = "http://localhost:8080/workers/";
    private final ReportResponse rp;
    private final WorkerModelMapper mapper;
    private final ModelReportSerializ serializ;

    public IndividualReportController(WorkerModelMapper mapper, ModelReportSerializ serializ, ReportResponse rp) {
        this.mapper = mapper;
        this.serializ = serializ;
        this.rp = rp;
    }

    @GetMapping(path = "/{id}")
    public Status reportWorkerById(@PathVariable("id") Long id) throws IOException {
        log.info("GET request /report/" + id);
        String fileName ="Individual[" + id + "].txt";

        Response<?> response = rp.getResponse(url+id);
        List<WorkerModel> entities = (List<WorkerModel>)response.getEntities();
        WorkerReportModel worker = mapper.toReportModuleById(entities.get(0));

        serializ.serializ("Name:" + worker.getName() + " Last Name: " + worker.getLastName(),fileName);
        for (String name : worker.getWorkHours()) {
            serializ.serializ(name, fileName);
        }

        return Status.OK;
    }

    @GetMapping(path = "/{id}/-c")
    public Status getById(@PathVariable("id") Long id) {
        log.info("GET request /report/" + id + "/-c");

        Response<?> response = rp.getResponse(url);
        List<WorkerModel> entities = (List<WorkerModel>) response.getEntities();
        WorkerReportModel worker = mapper.toReportModuleById(entities.get(0));


        System.out.println("Name:" + worker.getName() + " Last Name: " + worker.getLastName());
        for (String name : worker.getWorkHours()) {
            System.out.println(name);
        }

        return Status.OK;
    }


}

