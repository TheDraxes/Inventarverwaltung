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

import javax.rmi.ssl.SslRMIClientSocketFactory;
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
            System.out.println("**Bestehende Userdaten eingelesen");
        } else {
            userContainer.insertUser("admin","123");
            System.out.println("**Neue Userdatenbank mit standart Adminpasswort erstellt");
        }
        /*
        for(int i = 0; i < userContainer.getNumberOfUser(); i++){
            System.out.println(userContainer.getUserName(i) + " " + userContainer.getUserPasswort(i));
        }
        */
        System.out.println("**Login Fenster Initialisiert");
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
                System.out.println("**Eingeloggt als " + usernameField.getText());
                Stage lastWindow = (Stage) loginButton.getScene().getWindow();
                lastWindow.hide();
                new showStartWindow(this.userContainer);
            } else {
                System.out.println("**Nutzername oder Passwort falsch!");
            }
        } else {
            System.out.println("**Nutzername oder Passwort falsch!");
        }
    }
}
