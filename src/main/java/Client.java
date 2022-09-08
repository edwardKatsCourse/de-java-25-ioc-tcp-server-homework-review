import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class Client {
    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket("localhost", 8080);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject("");
        oos.flush();

        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        ois.readObject();
        System.out.println(ois.readUTF());
        oos.close();
        ois.close();
    }
}