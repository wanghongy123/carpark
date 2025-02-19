package com.github.wanghongy132.carpark.m.web.response;

import org.springframework.data.repository.Repository;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.github.wanghongy132.carpark.m.web.response.DefaultResponsePackagingActuator.DEFAULT_ACTUATOR;

/**
 * @author wanruxiuer
 */
public class ResponsePackagingActuatorManager {
    private final List<ResponsePackagingActuator> actuators;
    private final Map<Method, ResponsePackagingActuator> actuatorsCache;


    public ResponsePackagingActuatorManager(
            List<ResponsePackagingActuator> actuators
    ) {
        this.actuators = actuators;
        this.actuatorsCache = new HashMap<>();
    }

    public ResponsePackagingActuator getActuator(Method method, Class<?> handlerClass){
        return actuatorsCache.computeIfAbsent(method, m -> {
            for (ResponsePackagingActuator actuator : actuators) {
                if (actuator.supports(m, handlerClass)) {
                    return actuator;
                }
            }
            return DEFAULT_ACTUATOR;
        });
    }
}
