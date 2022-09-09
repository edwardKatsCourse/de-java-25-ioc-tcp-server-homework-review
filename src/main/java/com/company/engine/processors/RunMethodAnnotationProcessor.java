package com.company.engine.processors;

import com.company.engine.annotations.RunMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class RunMethodAnnotationProcessor {

    private Map<Class<?>, Object> appContext = new HashMap<>();

    public void runMethods() {
        // [1,2,3,4], [a,b,c,d,a,a,dsa], [123123], [asd,asd,ad,as,d,a]

        // flat map
        // 1,2,3,4,a,b,c,d,a,a,dsa
//
//        Map<Method, Object> methodsToRun = applicationContext
//                .entrySet()
//                .stream()
//                .flatMap(entry -> Arrays.stream(entry.getKey().getDeclaredMethods()))
//
//                // Tuple<T1, T2>
//                .filter(method -> method.isAnnotationPresent(RunMethod.class))
//                .collect(Collectors.toMap(
//                        method -> method,
//                        m -> applicationContext.get(m.getDeclaringClass())
//                ));

        Map<Method, Object> methodsToRun = new HashMap<>();

        appContext.forEach((serviceType, instance) -> {
            Method[] methods = serviceType.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(RunMethod.class)) {
                    methodsToRun.put(method, instance);
                }
            }
        });

        methodsToRun.forEach((method, instance) -> {
            try {
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
