package by.nintendo.controller;

import by.nintendo.Status;
import by.nintendo.mapper.DepartmentModelMapper;
import by.nintendo.util.ModelReportSerializ;
import by.nintendo.util.ReportResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import by.nintendo.model.*;
import by.nintendo.Response;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RequestMapping(path = "/report")
public class GeneralReportController {
    private final String url = "http://localhost:8080/department";
    private final ReportResponse rp;
    private final ModelReportSerializ serializ;
    private final DepartmentModelMapper mapper;

    public GeneralReportController(DepartmentModelMapper mapper, ModelReportSerializ serializ, ReportResponse rp) {
        this.mapper = mapper;
        this.serializ = serializ;
        this.rp = rp;
    }

    @GetMapping
    public Status reportDepartment() {
        log.info("GET request /report");
        Response<?> response = rp.getResponse(url);
        List<DepartmentModel> entities = (List<DepartmentModel>) response.getEntities();
        List<DepartmentReportModel> departments = mapper.toReportModule(entities);
        String fileName = "General_report.txt";
        for (DepartmentReportModel r : departments) {
            serializ.serializ("Department:" + r.getName(), fileName);
            serializ.serializ("Workers:", fileName);
            for (String name : r.getWorkers()) {
                serializ.serializ(name, fileName);
            }
        }
        return Status.OK;

    }

    @GetMapping(path = "/-c")
    public Status printConsoleDepartment() {
        log.info("GET request /report/-c");

        Response<?> response = rp.getResponse(url);
        List<DepartmentModel> entities = (List<DepartmentModel>) response.getEntities();
        List<DepartmentReportModel> departments = mapper.toReportModule(entities);

        String fileName = "General_report.txt";
        for (DepartmentReportModel r : departments) {
            System.out.println("Department:" + r.getName());
            System.out.println("Workers:");
            for (String name : r.getWorkers()) {
                System.out.println(name);
            }
        }
        return Status.OK;

    }
}
