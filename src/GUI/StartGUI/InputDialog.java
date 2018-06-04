package GUI.StartGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class InputDialog extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JTextField textField = new JTextField();

    public InputDialog(){

        this.setLayout(new GridLayout(2,1));
        label.setText("Namen Eingeben!");
        //a.add(textField);
        panel.add(label);
        panel.add(textField);
        getContentPane().add(panel);
        this.setSize(400,400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
