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
public class Response <M extends AbstractModel> {

    private String status;

    private List<M> entities;


    @Override
    public String toString() {
        return "Response{" +
                "status='" + status + '\'' +
                ", entities=" + entities +
                '}';
    }
}
