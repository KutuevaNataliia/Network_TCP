import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerConnectionProcessor extends Thread {
    private Socket socket;

    public ServerConnectionProcessor(Socket s) {
        socket = s;
    }

    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            int messageType = inputStream.readInt();
            if (messageType == Client.TEXT_MESSAGE) {
                String message = inputStream.readUTF();
                System.out.println("Получено сообщение: " + message);
            } else if (messageType == Client.DIAGRAM) {
                String sChart = inputStream.readUTF();
                System.out.println("Получена диаграмма");
                Diagram diagram = new Diagram(sChart);
            }
            sleep(1000);
            inputStream.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
