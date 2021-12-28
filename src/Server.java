import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

//сервер, принимающий сообщения
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Введите порт для приёма сообщений");
            //создание сканера для считывания данных с клавиатуры
            Scanner scanner = new Scanner(System.in);
            //считывание с клавиатуры номера порта, на который принимаются сообщения
            int port = scanner.nextInt();
            //закрытие сканера
            scanner.close();
            System.out.println("Server is running");
            //создание серверного сокета, ожидающего подключений
            ServerSocket serverSocket = new ServerSocket(port);
            while(true) {
                //создание сокета для подключения по протоколу TCP
                Socket socket = serverSocket.accept();
                //создание обработчика полученного сообщения
                ServerConnectionProcessor processor = new ServerConnectionProcessor(socket);
                //запуск обработки полученного сообщения
                processor.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
