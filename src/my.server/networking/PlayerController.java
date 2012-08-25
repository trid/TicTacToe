package my.server.networking;

import my.messages.serialized.*;
import my.server.FieldStatus;
import my.server.GameProcessor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created with IntelliJ IDEA.
 * User: trid
 * Date: 8/16/12
 * Time: 2:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class PlayerController {
    private Player player1 = new Player(FieldType.X, "");
    private Player player2 = new Player(FieldType.O, "");

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
            playerController.sleep();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerController(Socket p1, Socket p2) {
        this.p1Socket = p1;
        this.p2Socket = p2;

        p1Listener = new Thread(new Listener(p1Socket, player1));
        p1Listener.start();
        p2Listener = new Thread(new Listener(p2Socket, player2));
        p2Listener.start();

        player1.setPlayerSide(FieldType.X);
        player2.setPlayerSide(FieldType.O);
    }

    public void sleep(){
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void processMessage(Message message, Player player){
        System.out.println("Got message!");
        switch (message.getType()) {
            case XO_MESSAGE:
                System.out.println("Player tried to set mark");
                SetXOMessage setXOMessage = (SetXOMessage)message;
                FieldAnswerType answer = gameProcessor.putMark(setXOMessage.getX(), setXOMessage.getY(), player.getPlayerSide());
                Answer answerMessage = new Answer(answer, setXOMessage.getX(), setXOMessage.getY());
                if (player == player1){
                    sendMessage(p1Socket, answerMessage);
                    if (answer == FieldAnswerType.ACCEPTED)
                        sendMessage(p2Socket, setXOMessage);
                }
                else {
                    sendMessage(p2Socket, answerMessage);
                    if (answer == FieldAnswerType.ACCEPTED)
                        sendMessage(p1Socket, setXOMessage);
                }
                if (answer == FieldAnswerType.ACCEPTED)
                    checkFinished();
                break;
            case CHAT_MESSAGE:
                System.out.println(((ChatMessage)message).getMessage());
                if (player == player1)
                    sendMessage(p2Socket, message);
                else
                    sendMessage(p1Socket, message);
                break;
            case ANSWER_MESSAGE:
                break;
            case WHOAMI:
                if (player == player1){
                    System.out.println("Player 1 asks who is he");
                    Message toPlayer = new PlayerSideMessage(player1.getPlayerSide());
                    sendMessage(p1Socket, toPlayer);
                }
                else {
                    System.out.println("Player 2 asks who is he");
                    Message toPlayer = new PlayerSideMessage(player2.getPlayerSide());
                    sendMessage(p2Socket, toPlayer);
                }
        }
    }

    private void checkFinished() {
        FieldStatus status = gameProcessor.getStatus();
        if (status != FieldStatus.GAME_GOING){
            if (status == FieldStatus.FRIENDSHIP){
                GameOverMessage message = new GameOverMessage(GameResult.FRIENDSHIP);
                sendMessage(p1Socket, message);
                sendMessage(p2Socket, message);
            }
            else {
                GameOverMessage winner = new GameOverMessage(GameResult.WIN);
                GameOverMessage loser = new GameOverMessage(GameResult.LOSE);
                if (status == FieldStatus.WIN_X){
                    sendMessage(p1Socket, winner);
                    sendMessage(p2Socket, loser);
                }
                else {
                    sendMessage(p2Socket, winner);
                    sendMessage(p1Socket, loser);
                }
            }
        }
    }

    private void sendMessage(Socket socket, Message message) {
        OutputStream os = null;

        try {
            os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Listener implements Runnable {
        private Socket socket;
        private Player player;

        public Listener(Socket socket, Player player){
            this.socket = socket;
            this.player = player;
        }

        @Override
        public void run() {
            InputStream is = null;
            while (!socket.isClosed()){
                try {
                    is = socket.getInputStream();
                    ObjectInputStream ois = new ObjectInputStream(is);

                    Object obj = ois.readObject();
                    Message message = (Message)obj;
                    processMessage(message, player);

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
}
