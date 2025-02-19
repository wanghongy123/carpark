package com.github.wanghongy132.carpark.m.web.response;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanruxiuer
 */
@Data
@Configuration
@ConfigurationProperties("http.response.packer.annotation")
public class AnnotationResponsePackConfiguration {

    private Auto auto;

    @Data
    public static class Auto{

        private boolean enable = false;
        private String defaultSuccessMessage = "";
        private String defaultFailedMessage = "";
        private List<Class<?>> ignoreHandlerClasses = new ArrayList<>();
    }
}

