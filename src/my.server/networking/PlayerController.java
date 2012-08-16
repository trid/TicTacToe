package my.server.networking;

import my.messages.serialized.Message;

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

    public PlayerController(Socket p1, Socket p2) {
        this.p1Socket = p1;
        this.p2Socket = p2;
    }

    public void run(){
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

        }
    }
}
