package my.client;

import my.messages.serialized.ChatMessage;
import my.messages.serialized.FieldType;
import my.messages.serialized.Message;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processMessage(Message message){
        switch (message.getType()) {
            case CHAT_MESSAGE:
                Client.getClient().receiveChatMessage((ChatMessage)message);
                break;
            case XO_MESSAGE:
                break;
            case ANSWER_MESSAGE:
                break;
            default:
                break;
        }
    }

    private class MessageListener implements Runnable {

        @Override
        public void run() {

        }
    }
}
