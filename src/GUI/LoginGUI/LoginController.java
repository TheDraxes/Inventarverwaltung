package GUI.LoginGUI;

import GUI.StartGUI.StartController;
import GUI.StartGUI.showStartWindow;
import Verwaltung.UserContainerAlt;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.*;

/**
 * Kontrollerklasse für das Loginfenster.
 * Reagiert auf Userinteraktionen mit dem LoginGUI
 *
 * @author Tim
 * @version 1.0
 */

public class LoginController {
    // Button der den Login realisiert
    @FXML
    private Button loginButton;

    //Textfeld für die Usernameneingabe
    @FXML
    private TextField usernameField;

    //Passwortfeld
    @FXML
    private PasswordField passwordField;

    //Container Klasse für die userlogin Daten
    private UserContainerAlt userContainer = new UserContainerAlt();

    int loginTries = 0;

    private String lastUser = "";

    /**
     * Methode die das Login Fenster Initalisiert.
     *
     * Hier wird der Usercontainer falls vorhanden eingelesen.
     *
     * Falls kein Usercontainer bisher abgeschpeichert wurde wird ein neuer mit dem
     * Admin Account und dem Standartpasswort erstellt.
     *
     * @auther Tim
     * @version 1.0
     */

    @FXML
    public void initialize(){

        File userLogins = new File("user.dat");

        if(userLogins.exists()) {
            userContainer = new UserContainerAlt().loadUserData();
            System.out.println("**Bestehende Userdaten eingelesen");
        } else {
            userContainer.insertUser("admin","123");
            userContainer.safeUserData();
            System.out.println("**Neue Userdatenbank mit standart Adminpasswort erstellt");
        }
        System.out.println("**Login Fenster Initialisiert");
    }

    /**
     * Fängt Keyboard eingaben ab und ruft die loginButtonClicked funktion auf
     * falls die Entertaste gedrückt wurde.
     *
     * @auther Tim
     * @version 1.0
     * @see #loginButtonClicked()
     * @param event
     */

    @FXML
    void keyPressed(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            loginButtonClicked();
        }
    }

    /**
     * Methode die aufgerufen wird wenn der Loginbutton geclicked wurde
     *
     * Prüft die eingegebenen angaben, vergleicht sie mit denen in dem Usercontainer,
     * versteckt das aktuelle Fenster und Ruft das Inventarverwaltungsenster auf.
     *
     * @see showStartWindow#showStartWindow(UserContainerAlt, String)
     * @TODO sperrung nach 3 fehlgeschlagenen Loginversuchen
     * @auther Tim
     * @version 1.0
     */

    @FXML
    void loginButtonClicked() {
        if(userContainer.checkLogin(usernameField.getText(),passwordField.getText())){
            Stage lastWindow = (Stage) loginButton.getScene().getWindow();
            lastWindow.hide();
            new showStartWindow(this.userContainer, usernameField.getText());
        } else {
            if(loginTries == 0){
                lastUser = usernameField.getText();
                loginTries++;
            } else if(lastUser.equals(usernameField.getText())){
                loginTries++;
                lastUser = usernameField.getText();
            }
            if(!(loginTries == 3)) {
                new StartController().warnDialog(" Nutzername oder Passwort falsch!");
            } else {
                new StartController().warnDialog("Anmeldung gesperrt!");
            }
        }
    }
}
