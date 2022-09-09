package com.company.engine;

import com.company.engine.processors.PropertiesProcessor;
import com.company.engine.processors.RunMethodAnnotationProcessor;
import com.company.engine.processors.ServiceReaderProcessor;

import java.util.HashMap;
import java.util.Map;

public class Engine {

    private Map<Class<?>, Object> applicationContext = new HashMap<>();

    public void start() {
        // 1. find all @service classes and create them
        // 2. configure @service classes (inject @Value or @Autowired)
        // 3. find @TcpRequestMapping method
        // 4. run ServerSocket


        // 1.
        applicationContext = new ServiceReaderProcessor().findAndCreateServices();


        // 2.
        new PropertiesProcessor(applicationContext).loadAndConfigureServices();

        // 3.
        new RunMethodAnnotationProcessor().runMethods();

    }
}
