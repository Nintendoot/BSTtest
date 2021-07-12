package by.nintendo.mapper;

import by.nintendo.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Component
public class WorkerModelMapper {


    public List<WorkerReportModel> toReportModule(List<WorkerModel> listModel){
        log.info("Method toReportModule mapped listModel");
        List<WorkerReportModel> ds=new ArrayList<>();
        for(WorkerModel dd:listModel){
//            WorkerEntity workerEntity = workerMapper.toEntity(dd);
            WorkerReportModel workerReportModel = new WorkerReportModel();
            workerReportModel.setName(dd.getName());
            workerReportModel.setLastName(dd.getLastName());
            List<String> s=new ArrayList<>();
            for(WorkedHoursModel w:dd.getWorkHours()){
                StringBuilder time=new StringBuilder();
               time.append("StartWork: ").append(w.getStartWork()).append("  EndWork ").append(w.getEndWork());
                s.add(time.toString());
            }
            workerReportModel.setWorkHours(s);
            ds.add(workerReportModel);
        }
        return ds;

    }
}
