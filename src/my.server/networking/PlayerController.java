package my.server.networking;

import my.messages.serialized.Message;
import my.server.GameProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerController {
    private Player p1;
    private Player p2;

    private Socket p1Socket;
    private Socket p2Socket;

    private Thread p1Listener;
    private Thread p2Listener;

    GameProcessor gameProcessor = GameProcessor.getInstance();

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(1990);
            //Broot and not clever hack. But for such little project it can work
            Socket p1 = serverSocket.accept();
            System.out.println("Player 1 connected");
            Socket p2 = serverSocket.accept();
            System.out.println("Player 2 connected");

            PlayerController playerController = new PlayerController(p1, p2);
            playerController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerController(Socket p1, Socket p2) {
        this.p1Socket = p1;
        this.p2Socket = p2;

        p1Listener = new Thread(new Listener(p1Socket));
        p2Listener = new Thread(new Listener(p2Socket));
    }

    public void run(){
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void processMessage(Message message){

    }

    private class Listener implements Runnable {
        private Socket socket;

        public Listener(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            InputStream is = null;
            try {
                is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                while (!socket.isClosed()){
                    Message message = (Message)ois.readObject();
                    processMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
