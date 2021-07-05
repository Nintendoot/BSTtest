package by.nintendo.controller;

import by.nintendo.Response;
import by.nintendo.Status;
import by.nintendo.mapper.WorkerModelMapper;
import by.nintendo.model.WorkerModel;
import by.nintendo.model.WorkerReportModel;
import by.nintendo.util.ModelReportSerializ;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RequestMapping(path = "/report")
public class IndividualReportController {
    private final WorkerModelMapper mapper;
    private final ModelReportSerializ serializ;

    public IndividualReportController(WorkerModelMapper mapper, ModelReportSerializ serializ) {
        this.mapper = mapper;
        this.serializ = serializ;
    }

    @GetMapping(path = "/{id}")
    public Status getById(@PathVariable("id") Long id) {
        log.info("GET request /report/" + id);
        WebClient webClient = WebClient.create();
        String url = "http://localhost:8080/workers/";
        String fileName = "Individual[" + id + "].txt";

        Response<?> block = webClient.get()
                .uri(url+id)
                .retrieve().bodyToMono(Response.class).block();
       if(block!=null){
           List<WorkerModel> entities = (List<WorkerModel>) block.getEntities();
           List<WorkerReportModel> worker = mapper.toReportModule(entities);

           for (WorkerReportModel r : worker) {
               serializ.serializ("Name:" + r.getName() + " Last Name: " + r.getLastName(), fileName);
               for (String name : r.getWorkHours()) {
                   serializ.serializ(name, fileName);
               }
           }
           return Status.OK;
       }else{
           return Status.BAD;
       }


    }
}
