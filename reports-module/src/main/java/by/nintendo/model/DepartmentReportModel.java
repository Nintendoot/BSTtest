package by.nintendo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentReportModel{
    private String name;
    private List<String> workers;

    @Override
    public String toString() {
        return "DepartReport{" +
                "name='" + name + '\'' +
                ", workerName=" + workers +
                '}';
    }
}
