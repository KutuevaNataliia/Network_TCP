import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Введите порт для приёма сообщений");
            Scanner scanner = new Scanner(System.in);
            int port = scanner.nextInt();
            scanner.close();
            System.out.println("Server is running");
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                Socket socket = serverSocket.accept();
                ServerConnectionProcessor processor = new ServerConnectionProcessor(socket);
                processor.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
