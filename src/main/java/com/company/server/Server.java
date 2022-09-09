package com.company.server;

import com.company.JacksonSerializer;
import com.company.User;
import com.company.engine.annotations.*;
import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

@Service
public class Server {
    @Value("server.port")
    private Integer port;

    private String path = "json-data.txt";

    @SneakyThrows
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                     ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

                    String data = ois.readUTF();
                    User user = receiveRequest(data);
                    String result = JacksonSerializer.serializeObjectToJSONString(user);
                    oos.writeUTF(result);
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Set<Socket> users = new HashSet<>();
//        ServerSocket serverSocket = new ServerSocket(1231);
//
//        while (true) {
//            try (Socket socketFromClient = serverSocket.accept();) {
//                ...
//
//                String data = socketFromClient.getInputStream().toString();
//                socketFromClient.getOutputStream().write("response");
//            }
//        }
//    }

    /**
     * - Engine creates `ServerSocket` internally.
     * - Whenever `.accept()` triggers - the engine reads `InputStream` and receives `String data` from it
     * - Insert `String data` to the method (for `@Payload` annotation method parameter)
     * - Any method response - serialize using `Jackson` and send back to client
     * - Close client connection
     */
    @TcpRequestMapping
    public User receiveRequest(@Payload String data) {

        return new User("Peter", 47);
    }











    /**
     * 1. `receiveRequest()` should be called in a separate `Thread`
     * 2. `@Payload` now can be deserialized to any object
     *     1. Read class type
     *     2. Use it for deserialization
     *     3. Set the result to the method
     */
    @TcpRequestMapping
    public User receiveRequest(@Payload User request) {

        return new User(request.getName().toUpperCase(), request.getAge() + 1);
    }
}
