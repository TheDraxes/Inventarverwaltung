package GUI.LoginGUI;

import GUI.StartGUI.showStartWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;


public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private UserContainer userContainer = new UserContainer();

    @FXML
    public void initialize(){

        File userLogins = new File("user.dat");

        if(userLogins.exists()) {
            userContainer = new UserContainer().loadUserData();
        } else {
            userContainer.insertUser("admin","1234");
        }

        for(int i = 0; i < userContainer.getNumberOfUser(); i++){
            System.out.println(userContainer.getUserName(i) + " " + userContainer.getUserPasswort(i));
        }
    }

    @FXML
    void newUserClicked(ActionEvent event){
        NewUserJPanel newUserJPanel = new NewUserJPanel();
        int result;
        boolean isDuplicate;
        boolean pwConfirmed;

        while (true) {
            result = JOptionPane.showConfirmDialog(null, newUserJPanel, "Benutzer anlegen!", JOptionPane.OK_CANCEL_OPTION);

            isDuplicate = this.userContainer.userIsDuplicate(newUserJPanel.getUsername());
            pwConfirmed = newUserJPanel.getPassword().equals(newUserJPanel.getConfirmpw());

            if(result == JOptionPane.CANCEL_OPTION){
                System.out.println("Vorgang abgebrochen");
                break;
            } else if(newUserJPanel.usernameIsEmpty() || newUserJPanel.passwordIsEmpty()) {
                JOptionPane.showMessageDialog(null,"Felder dürfen nicht leer sein!");
            } else if (!isDuplicate && pwConfirmed){
                userContainer.insertUser(newUserJPanel.getUsername(), newUserJPanel.getPassword());
                userContainer.safeUserData();
                JOptionPane.showMessageDialog(null,"Neuen Benutzer angelegt!");
                initialize();
                break;
            } else if(!pwConfirmed){
                JOptionPane.showMessageDialog(null,"Passwörter stimmen nicht überein!");
            } else if(isDuplicate){
                JOptionPane.showMessageDialog(null,"Username bereits belegt!");
                }
            }
    }

    @FXML
    void keyPressed(KeyEvent event){

        if(event.getCode().equals(KeyCode.ENTER)){
            loginButtonClicked();
        }
    }

    @FXML
    void loginButtonClicked() {

        int index = -1;
        for(int i = 0 ; i < userContainer.getNumberOfUser(); i++){
                if (userContainer.getUserName(i).equals(usernameField.getText())) {
                    index = i;
                }
        }

        if(index != -1) {
            if (usernameField.getText().equals(userContainer.getUserName(index)) && passwordField.getText().equals(userContainer.getUserPasswort(index))) {
                Stage lastWindow = (Stage) loginButton.getScene().getWindow();
                lastWindow.hide();
                new showStartWindow();
            } else {
                System.out.println("Nutzername oder Passwort falsch!");
            }
        } else {
            System.out.println("Nutzername oder Passwort falsch!");
        }
    }
}
