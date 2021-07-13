package by.nintendo.util;

import by.nintendo.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ReportResponse {
    public Response<?> getResponse(String url){
        WebClient webClient = WebClient.create();
        Response<?> block = webClient.get()
                .uri(url)
                .retrieve().bodyToMono(Response.class).block();
        assert block != null;
        return block;
    }
}
