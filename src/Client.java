import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

//клиент, отправляющий сообщения
public class Client {

    //номер, соответствующий простому текстовому сообщению
    public static final int TEXT_MESSAGE = 1;
    //номер, соответствующий диаграмме
    public static final int DIAGRAM = 2;

    //отправить простое текстовое сообщение
    public void sendTextMessage(String host, int port, String message) {
        try {
            System.out.println("Client is running");
            //создать сокет для подключения по протоколу TCP
            Socket socket = new Socket(host, port);
            //получить выходной поток для сокета
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            //записать в выходной поток число, соответствующее простому текстовому сообщению
            outputStream.writeInt(TEXT_MESSAGE);
            //записать в выходной поток строку сообщения в кодировке UTF
            outputStream.writeUTF(message);
            //закрыть выходной поток
            outputStream.close();
            //закрыть сокет и разорвать соединение
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDiagramData(String host, int port, String sChart) {
        try {
            System.out.println("Client is running");
            //создать сокет для подключения по протоколу TCP
            Socket socket = new Socket(host, port);
            //получить выходной поток для сокета
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            //записать в выходной поток число, соответствующее диаграмме
            outputStream.writeInt(DIAGRAM);
            //записать в выходной поток строку данных для диаграммы в кодировке UTF
            outputStream.writeUTF(sChart);
            //закрыть выходной поток
            outputStream.close();
            //закрыть сокет и разорвать соединение
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Введите IP-адрес сервера");
        //создание сканера для считывания данных с клавиатуры
        Scanner scanner = new Scanner(System.in);
        //считывание с клавиатуры IP-адреса сервера
        String host = scanner.nextLine();
        System.out.println("Введите номер порта");
        //считывание с клавиатуры номера порта сервера, на который принимаются сообщения
        int port = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите сообщение для передачи");
        //считывание с клавиатуры текстового сооющения
        String message = scanner.nextLine();
        //создание клиента
        Client client = new Client();
        //отправка простого текстового сообщения на сервер
        client.sendTextMessage(host, port, message);
        System.out.println("Введите имя файла с данными для диаграммы");
        //считывание с клавиатуры имени файла
        String filePath = scanner.nextLine();
        //закрытие сканера
        scanner.close();
        //получение пути к файлу по имени файла
        Path path = Paths.get(filePath);
        //считывание из файла строки с данными для диаграммы
        String angles = Files.readString(path);
        //отправка данных для диаграммы на сервер
        client.sendDiagramData(host, port, angles);
    }
}
