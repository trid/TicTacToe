package my.client;

import my.messages.serialized.FieldType;
import my.messages.serialized.Message;

import java.io.IOException;

import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 18.08.12
 * Time: 1:25
 */
public class ClientController {
    private Socket socket;
    private FieldType player;
    private String playerName;

    public ClientController(String serverAddr, String playerName){
        try {
            socket = new Socket(serverAddr, 1990);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message){

    }

    public void processMessage(){

    }

    private class MessageListener implements Runnable {

        @Override
        public void run() {

        }
    }
}
