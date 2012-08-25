package my.client;

import my.messages.serialized.*;

import java.io.*;

import java.net.Socket;
import java.net.SocketException;

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

        this.playerName = playerName;
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
            case XO_MESSAGE:
                Client.getClient().receiveSetMark((SetXOMessage)message);
                break;
            case CHAT_MESSAGE:
                System.out.println("Got chat message");
                Client.getClient().receiveChatMessage((ChatMessage)message);
                break;
            case ANSWER_MESSAGE:
                System.out.println("Got answer from game server");
                Client.getClient().receiveAnswer((Answer)message);
                break;
            case PLAYER_SIDE:
                System.out.println("Got player side");
                Client.getClient().setPlayerSide(((PlayerSideMessage) message).getPlayerType());
                break;
            case GAME_OVER_MESSAGE:
                System.out.println("Game over");
                Client.getClient().gameOver((GameOverMessage)message);
                break;
        }
    }

    @Override
    public void run() {
        InputStream is = null;
        while (!socket.isClosed()){
            try {
                is = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Message message = (Message)ois.readObject();
                processMessage(message);
            } catch (SocketException e) {
                e.printStackTrace();
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
