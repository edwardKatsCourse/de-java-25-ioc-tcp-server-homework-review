import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesFileReader {

    @SneakyThrows
    public static String read() {

        try (InputStream input = PropertiesFileReader.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            return prop.getProperty("server.port");
        }
    }
}
