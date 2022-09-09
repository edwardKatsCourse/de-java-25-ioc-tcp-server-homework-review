package com.company.engine.processors;

import com.company.PropertiesFileReader;
import com.company.engine.annotations.Value;

import java.lang.reflect.Field;
import java.util.Map;

public class PropertiesProcessor {

    private Map<Class<?>, Object> appContext;

    public PropertiesProcessor(Map<Class<?>, Object> appContext) {
        this.appContext = appContext;
    }

    public void loadAndConfigureServices() {
        PropertiesFileReader.loadProperties();
        // service 1
        //  - field 1
        //  - field 2
        //  - field 3

        appContext.forEach((type, instance) -> {
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Value.class)) {
                    Value value = field.getAnnotation(Value.class);
                    // "server.port" -> System.getProperty("server.port") -> 8080
                    String propertyKey = value.value();

                    field.setAccessible(true);
                    try {
                        field.set(instance, System.getProperty(propertyKey));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }
}
