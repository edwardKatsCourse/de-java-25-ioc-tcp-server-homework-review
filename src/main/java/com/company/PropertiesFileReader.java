package com.company;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PropertiesFileReader {

//    private final Map<String, String> properties = new HashMap<>();

    @SneakyThrows
    public static void loadProperties() {

        try (InputStream input = PropertiesFileReader.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            System.setProperties(prop);
        }
    }

//    public String getProperty(String key) {
//        return properties.get(key);
//    }
}
