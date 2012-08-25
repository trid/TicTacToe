package my.client.swing;

import my.client.Client;
import my.client.UICallBack;
import my.messages.serialized.Answer;
import my.messages.serialized.ChatMessage;
import my.messages.serialized.FieldType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 19.08.12
 * Time: 2:02
 */
public class MainForm extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private final ChatPanel chatPanel;
    private final TicTacToePanel ticTacToePanel;

    private FieldType[][] field = new FieldType[3][3];

    public MainForm(){
        chatPanel = new ChatPanel();
        ticTacToePanel = new TicTacToePanel();

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

        add(chatPanel, BorderLayout.SOUTH);
        add(ticTacToePanel, BorderLayout.CENTER);

        Client.getClient().setCallBack(new MyUICallBack());
        clearField();
    }

    private void clearField() {
        for (int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++)
                field[i][k] = FieldType.EMPTY;
    }

    private final class ChatPanel extends JPanel{
        private final JTextArea chat;
        private final JTextField messageText;

        public ChatPanel(){
            super(new BorderLayout());
            chat = new JTextArea(5, 600);
            chat.setWrapStyleWord(true);
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

        private class ChatListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                Client.getClient().sendChatMessage(messageText.getText());
                chat.append("Me: " + messageText.getText() + "\n");
                messageText.setText("");
            }
        }

        public void addMessage(ChatMessage message){
            chat.append(message.getPlayerName() + ": " + message.getMessage() + "\n");
        }
    }

    private class TicTacToePanel extends JPanel{
        public TicTacToePanel(){
            addMouseListener(new PanelActionListener());
        }

        @Override
        protected void paintComponent(Graphics g) {
            int xCellSize = getWidth()/3;
            int yCellSize = getHeight()/3;

            g.drawLine(0, yCellSize, getWidth(), yCellSize);
            g.drawLine(0, yCellSize*2, getWidth(), yCellSize*2);
            g.drawLine(xCellSize, 0, xCellSize, getHeight());
            g.drawLine(xCellSize*2, 0, xCellSize*2, getHeight());

            for (int i = 0; i < 3; i++)
                for (int k = 0; k < 3; k++){
                    if (field[i][k] == FieldType.X){
                        g.drawLine(xCellSize*i, yCellSize*k, xCellSize*(i + 1), yCellSize*(k + 1));
                        g.drawLine(xCellSize*i, yCellSize*(k + 1), xCellSize*(i + 1), yCellSize*k);
                    }
                    else if (field[i][k] == FieldType.O){
                        g.drawOval(xCellSize*i, yCellSize*k, xCellSize, yCellSize);
                    }
                }

        }

        private class PanelActionListener implements MouseListener{

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                int xCellSize = getWidth()/3;
                int yCellSize = getHeight()/3;

                Client.getClient().sendSetMessage(x/xCellSize, y/yCellSize);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        }
    }

    private class MyUICallBack implements UICallBack{

        @Override
        public void receiveChatMessage(ChatMessage message) {
            chatPanel.addMessage(message);
        }

        @Override
        public void receiveAnswer(Answer message) {
            switch (message.getAnswerType()) {
                case ACCEPTED:
                    field[message.getX()][message.getY()] = Client.getClient().getPlayerSide();
                    repaint();
                    break;
                case ALREADY_SET:
                    break;
                case NOT_YOUR_TURN:
                    break;
                case GAME_ALREADY_OVER:
                    break;
                case EMPTY_FIELD_SETTING:
                    break;
            }
        }

        @Override
        public void setMark(int x, int y, FieldType markType) {
            field[x][y] = markType;
            repaint();
        }

        @Override
        public void resultWin() {
            JOptionPane.showMessageDialog(MainForm.this, "You win!");
        }

        @Override
        public void resultLose() {
            JOptionPane.showMessageDialog(MainForm.this, "You lose!");
        }

        @Override
        public void resultFriendship() {
            JOptionPane.showMessageDialog(MainForm.this, "No winner! (But no losers to ;)");
        }
    }
}
