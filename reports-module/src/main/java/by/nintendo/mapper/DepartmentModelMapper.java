package by.nintendo.mapper;

import by.nintendo.model.DepartmentModel;
import by.nintendo.model.DepartmentReportModel;
import by.nintendo.model.WorkerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class DepartmentModelMapper {

    public List<DepartmentReportModel> toReportModule(List<DepartmentModel> listModel) {
        log.info("Method toReportModule mapped DepartmentModel.");

        List<DepartmentReportModel> ds = new ArrayList<>();

        for (DepartmentModel dd : listModel) {
            DepartmentReportModel departReport = new DepartmentReportModel();
            departReport.setName(dd.getName());
            List<String> s = new ArrayList<>();

            for (WorkerModel w : dd.getWorkers()) {
                StringBuilder worker = new StringBuilder();
                worker.append("Name: ").append(w.getName()).append("  LastName: ").append(w.getLastName()).append("  Position: ").append(w.getPosition());
                s.add(worker.toString());
            }

            departReport.setWorkers(s);
            ds.add(departReport);
        }
        return ds;
    }


}
