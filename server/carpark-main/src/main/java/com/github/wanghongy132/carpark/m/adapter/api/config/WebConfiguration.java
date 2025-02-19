package com.github.wanghongy132.carpark.m.adapter.api.config;

import com.github.wanghongy132.carpark.m.web.response.*;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfiguration extends WebMvcAutoConfiguration {
    @Bean
    public ResponsePackagingActuatorManager responsePackagingActuatorManager(
            AnnotationResponsePackConfiguration configuration,
            ResponseBuilder<?> responseBuilder
    ) {
        List<ResponsePackagingActuator> actuators = new ArrayList<>();
        actuators.add(new ErrorPackagingActuator(responseBuilder));
        actuators.add(new AnnotationPackagingActuator(configuration, responseBuilder));
        return new ResponsePackagingActuatorManager(actuators);
    }
}
