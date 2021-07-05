package by.nintendo.controller;

import by.nintendo.Status;
import by.nintendo.mapper.DepartmentModelMapper;
import by.nintendo.util.ModelReportSerializ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import by.nintendo.model.*;
import by.nintendo.Response;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RestControllerAdvice
@RequestMapping(path = "/report")
public class GeneralReportController {

    private final ModelReportSerializ serializ;
    private final DepartmentModelMapper mapper;

    public GeneralReportController(DepartmentModelMapper mapper, ModelReportSerializ serializ) {
        this.mapper = mapper;
        this.serializ = serializ;
    }

    @GetMapping
    public Status getAll() {
        log.info("GET request /report");
        WebClient webClient = WebClient.create();
        String url = "http://localhost:8080/department";
        String fileName = "General_report.txt";

        Response<?> block = webClient.get()
                .uri(url)
                .retrieve().bodyToMono(Response.class).block();
        assert block != null;
        List<DepartmentModel> entities = (List<DepartmentModel>) block.getEntities();
        List<DepartmentReportModel> departmens = mapper.toReportModule(entities);

        for (DepartmentReportModel r : departmens) {
            serializ.serializ("Department:" + r.getName(), fileName);
            serializ.serializ("Workers:", fileName);
            for (String name : r.getWorkers()) {
                serializ.serializ(name, fileName);
            }

        }
        return Status.OK;

    }
}
