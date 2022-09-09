package com.company.engine.processors;

import com.company.engine.annotations.Service;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ServiceReaderProcessor {

    public Map<Class<?>, Object> findAndCreateServices() {
        // 1.
        Map<Class<?>, Object> services = new HashMap<>();

        new Reflections("com.company.engine")
                .getTypesAnnotatedWith(Service.class)
                .forEach((Class<?> serviceClassType) -> {
                    try {
                        var serverInstance = serviceClassType.getConstructors()[0].newInstance();
                        services.put(serviceClassType, serverInstance);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                });

        return services;
    }
}
