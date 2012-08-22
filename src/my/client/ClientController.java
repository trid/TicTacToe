package my.client;

import my.messages.serialized.ChatMessage;
import my.messages.serialized.FieldType;
import my.messages.serialized.Message;

import java.io.IOException;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 18.08.12
 * Time: 1:25
 */
public class ClientController implements Runnable {
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

    public void processMessage(Message message){
        switch (message.getType()) {
            case XO_MESSAGE:
                break;
            case CHAT_MESSAGE:
                Client.getClient().receiveChatMessage((ChatMessage)message);
                break;
            case ANSWER_MESSAGE:
                break;
        }
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
