package by.nintendo.mapper;

import by.nintendo.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WorkerModelMapper {


    public WorkerReportModel toReportModuleById(List<WorkerModel> listModel, Long id) {
        log.info("Method toReportModule mapped listModel");

        WorkerReportModel ds = new WorkerReportModel();

        for (WorkerModel dd : listModel) {
            if (dd.getId().equals(id)) {
                ds.setName(dd.getName());
                ds.setLastName(dd.getLastName());
                List<String> s = new ArrayList<>();

                for (WorkedHoursModel w : dd.getWorkHours()) {
                    StringBuilder time = new StringBuilder();
                    time.append("StartWork: ").append(w.getStartWork()).append("  EndWork ").append(w.getEndWork());
                    s.add(time.toString());
                }

                ds.setWorkHours(s);
            }

        }
        return ds;

    }
}
