package GUI.LoginGUI;

import GUI.StartGUI.StartController;
import GUI.StartGUI.showStartWindow;
import Verwaltung.UserContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
            userContainer.safeUserData();
            System.out.println("**Neue Userdatenbank mit standart Adminpasswort erstellt");
        }
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

        System.out.println(userContainer.checkLogin(usernameField.getText(),passwordField.getText()));

        if(userContainer.checkLogin(usernameField.getText(),passwordField.getText())){
            Stage lastWindow = (Stage) loginButton.getScene().getWindow();
            lastWindow.hide();
            new showStartWindow(this.userContainer, usernameField.getText());
        } else {
            new StartController().warnDialog(" Nutzername oder Passwort falsch!");
        }
    }
}
