import lombok.SneakyThrows;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        Server server = new Server();
        server.run();

        Thread separateThread = new Thread() {
            @Override
            public void run() {
                super.run();
            }
        };
        separateThread.join();
        separateThread.start();
    }
}
