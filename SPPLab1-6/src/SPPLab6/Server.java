package SPPLab6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server {

    public static final int PORT = 8080;
    public static Queue<ServerLoad> serverList = new ConcurrentLinkedQueue<>();
    
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Сервер запущен и слушает на порту " + PORT);
        try {
            while (true) {
            	//Принимаем входящие подключения и создаем обработчик для клиента
                Socket socket = server.accept();
                try {
                    serverList.add(new ServerLoad(socket));
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }


}
