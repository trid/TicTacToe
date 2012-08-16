package my.server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 3:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Server {
    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket();
            //Broot and not clever hack. But for such little project it can work
            Socket p1 = serverSocket.accept();
            Socket p2 = serverSocket.accept();

            PlayerController playerController = new PlayerController(p1, p2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
