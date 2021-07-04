package by.nintendo;

import by.nintendo.entity.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.List;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@SpringBootApplication
public class CrudModuleApplication {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        return mapper;
    }

//    @Bean
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    public Response<?> getResponse(){
//        Response<?> response=new Response<>();
//        return response;
//    }
    public static void main(String[] args) {
        SpringApplication.run(CrudModuleApplication.class, args);
    }
}
