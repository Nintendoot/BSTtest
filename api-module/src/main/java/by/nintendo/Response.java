package by.nintendo;

import by.nintendo.model.AbstractModel;
import by.nintendo.model.WorkerModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private String errors;

    private List<WorkerModel> entities;

    @Override
    public String toString() {
        return "Response{" +
                "errors='" + errors + '\n' +
                ", entities=" + entities +
                '}';
    }
}
