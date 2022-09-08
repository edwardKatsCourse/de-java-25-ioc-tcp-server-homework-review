import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;

import java.io.StringWriter;

public class JacksonSerializer {

    @SneakyThrows
    public static User parseJSONStringToObject(String jsonData) {

        ObjectMapper objectMapper = new ObjectMapper();

        User user = objectMapper.readValue(jsonData, User.class);

        return user;


    }

    @SneakyThrows
    public static String serializeObjectToJSONString(User user) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        StringWriter stringUser = new StringWriter();
        objectMapper.writeValue(stringUser, user);

        return stringUser.toString();
    }

}
