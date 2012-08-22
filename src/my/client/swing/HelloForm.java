package my.client.swing;

import my.client.Client;
import my.client.ClientController;
import sun.awt.VerticalBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: TriD
 * Date: 18.08.12
 * Time: 1:40
 */
public class HelloForm extends JFrame {
    private JTextField addr;
    private JTextField name;
    private JButton start;
    private JLabel nameLabel;
    private JLabel addrLabel;

    public HelloForm(){
        setSize(300, 200);
        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(7, 1);
        panel.setLayout(layout);
        addrLabel = new JLabel("Set game server address");
        addr = new JTextField();
        nameLabel = new JLabel("Set your name here");
        name = new JTextField();
        start = new JButton("Start");
        panel.add(addrLabel);
        panel.add(addr);
        panel.add(nameLabel);
        panel.add(name);
        panel.add(start);
        add(panel);
        start.addActionListener(new StartListener());
        addr.addActionListener(new StartListener());
        name.addActionListener(new StartListener());
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                HelloForm form = new HelloForm();
                form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                form.setVisible(true);
            }
        });
    }

    private class StartListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String address = addr.getText();
            String name = addr.getText();
            Client.getClient().createClientController(address, name);
            MainForm mainForm = new MainForm();
            mainForm.setVisible(true);
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            setVisible(false);
        }
    }
}
