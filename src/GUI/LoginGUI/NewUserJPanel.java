package GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;

public class NewUserJPanel extends JPanel{
    private JTextField username = new JTextField(20);
    private JPasswordField password = new JPasswordField(20);
    private JPasswordField confirmpw = new JPasswordField(20);

    public NewUserJPanel(){
        setLookAndFeel();

        this.setLayout(new GridLayout(3,1));

        this.add(new JLabel("Nutzername: "));
        this.add(username);


        this.add(new JLabel("Passwort: "));
        this.add(password);


        this.add(new JLabel("Passwort bestätigen: "));
        this.add(confirmpw);

    }

    public String getUsername() {
        return username.getText();
    }

    public boolean usernameIsEmpty(){
        if(username.getText().equals("") || username.getText() == null){
            return true;
        } else {
            return false;
        }
    }

    public void setUsername(JTextField username) {
        this.username = username;
    }

    public String getPassword() {
        return password.getText();
    }

    public boolean passwordIsEmpty(){
        if(password.getText().equals("") || password.getText() == null){
            return true;
        } else {
            return false;
        }
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }

    public String getConfirmpw() {
        return confirmpw.getText();
    }

    public void setConfirmpw(JPasswordField confirmpw) {
        this.confirmpw = confirmpw;
    }

    private void setLookAndFeel(){
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
