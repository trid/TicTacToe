package my.client.swing;

import my.client.Client;
import my.client.UICallBack;
import my.messages.serialized.ChatMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setLayout(layout);

        //chatPanel.setSize(WIDTH/2, HEIGHT);
        //ticTacToePanel.setSize(WIDTH/2, HEIGHT);

        add(chatPanel, BorderLayout.SOUTH);
        add(ticTacToePanel, BorderLayout.CENTER);
    }

    private class ChatPanel extends JPanel{
        private final JTextPane chat;
        private final JTextField messageText;

        public ChatPanel(){
            super(new BorderLayout());
            chat = new JTextPane();
            chat.setEnabled(false);
            messageText = new JTextField();
            messageText.addActionListener(new ChatListener());
            JButton sendMessage = new JButton("Send");
            sendMessage.addActionListener(new ChatListener());
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(messageText);
            panel.add(sendMessage, BorderLayout.EAST);

            add(chat, BorderLayout.NORTH);
            add(panel, BorderLayout.SOUTH);
        }

        public void addChatMessage(ChatMessage message){
            chat.setText(chat.getText() + '\n' + message.getName() + ": " + message.getText());
        }

        private class ChatListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Client.getClient().sendChatMessage(messageText.getText());
            }
        }
    }

    private class TicTacToePanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {

        }
    }

    private class GameCallBack implements UICallBack {

        @Override
        public void receiveChatMessage(ChatMessage message) {

        }
    }
}
