import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Client {

    public static final int TEXT_MESSAGE = 1;
    public static final int DIAGRAM = 2;

    public void sendTextMessage(String host, int port, String message) {
        try {
            System.out.println("Client is running");
            Socket socket = new Socket(host, port);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(TEXT_MESSAGE);
            outputStream.writeUTF(message);
//            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
//            String result = inputStream.readUTF();
//            System.out.println("Result is " + result);
//            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDiagramData(String host, int port, String sChart) {
        try {
            System.out.println("Client is running");
            Socket socket = new Socket(host, port);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            outputStream.writeInt(DIAGRAM);
            outputStream.writeUTF(sChart);
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        String host;
        int port = 1099;

        System.out.println("Введите IP-адрес сервера");
        Scanner scanner = new Scanner(System.in);
        host = scanner.nextLine();
        System.out.println("Введите номер порта");
        port = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите сообщение для передачи");
        String message = scanner.nextLine();

        Client client = new Client();
        client.sendTextMessage(host, port, message);

        System.out.println("Введите имя файла с данными для диаграммы");
        String filePath = scanner.nextLine();
        scanner.close();
        Path path = Paths.get(filePath);
        String angles = Files.readString(path);
        client.sendDiagramData(host, port, angles);
    }
}
