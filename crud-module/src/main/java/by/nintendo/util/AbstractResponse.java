package by.nintendo.util;

import by.nintendo.model.AbstractModel;
import org.springframework.stereotype.Component;
import by.nintendo.Response;

import java.util.List;
@Component
public class AbstractResponse  <M extends AbstractModel>{

    public Response<?> getResponse(String name, List<M> list){
        return new Response<>(name,list);
    }
}
