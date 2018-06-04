package GUI.StartGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputDialog extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JLabel label = new JLabel();
    JTextField textField = new JTextField();
    JButton confirm = new JButton();

    public InputDialog(){

        panel.setLayout(new GridLayout(3,1));
        bottomPanel.setLayout(new GridLayout(1, 3));

        label.setText("Namen Eingeben!");
        confirm.addActionListener(this);

        panel.add(label);
        panel.add(textField);

        bottomPanel.add(new JLabel());
        bottomPanel.add(confirm);
        bottomPanel.add(new JLabel());
        panel.add(bottomPanel);
        getContentPane().add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
    }
}
