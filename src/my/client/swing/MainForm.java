package my.client.swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 19.08.12
 * Time: 2:02
 */
public class MainForm extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public MainForm(){
        ChatPanel chatPanel = new ChatPanel();
        TicTacToePanel ticTacToePanel = new TicTacToePanel();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        //setLayout(layout);

        //chatPanel.setSize(WIDTH/2, HEIGHT);
        //ticTacToePanel.setSize(WIDTH/2, HEIGHT);

        add(chatPanel, BorderLayout.WEST);
        //add(ticTacToePanel, BorderLayout.EAST);
    }

    private class ChatPanel extends JPanel{
        public ChatPanel(){
            JTextPane chat = new JTextPane();
            JTextField message = new JTextField();
            BorderLayout layout = new BorderLayout();
            setLayout(layout);
            //chat.setSize(WIDTH/2, HEIGHT - message.getHeight());
            //message.setSize(WIDTH/2, message.getHeight());

            add(chat, BorderLayout.SOUTH);
            add(message, BorderLayout.NORTH);
        }
    }

    private class TicTacToePanel extends JPanel{

    }
}
