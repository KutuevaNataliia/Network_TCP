import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

//обработчик полученных сообщений
public class ServerConnectionProcessor extends Thread {
    //сокет для соединения по протоколу TCP
    private Socket socket;

    public ServerConnectionProcessor(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            //получить входной поток для сокета
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            //считать из входного потока число, соответствующее типу сообщения
            int messageType = inputStream.readInt();
            //считать из входного потока строку в кодировке UTF
            String message = inputStream.readUTF();
            if (messageType == Client.TEXT_MESSAGE) {
                //вывести простое текстовое сообщение в консоль
                System.out.println("Получено сообщение: " + message);
            } else if (messageType == Client.DIAGRAM) {
                //вывести в консоль сообщение о получении диаграммы
                System.out.println("Получена диаграмма");
                //создать диаграмму на основе полученного сообщения
                Diagram diagram = new Diagram(message);
            }
            //закрыть входной поток
            inputStream.close();
            //закрыть сокет и разорвать соединение
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
