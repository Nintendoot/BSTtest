package by.nintendo.mapper;

import by.nintendo.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WorkerModelMapper {
    public WorkerReportModel toReportModuleById(WorkerModel workerModel) {
        log.info("Method toReportModule mapped listModel");

        WorkerReportModel ds = new WorkerReportModel();
                ds.setName(workerModel.getName());
                ds.setLastName(workerModel.getLastName());
                List<String> s = new ArrayList<>();

                for (WorkedHoursModel w : workerModel.getWorkHours()) {
                    StringBuilder time = new StringBuilder();
                    time.append("StartWork: ").append(w.getStartWork()).append("  EndWork ").append(w.getEndWork());
                    s.add(time.toString());
                }

                ds.setWorkHours(s);
        return ds;

    }
}
