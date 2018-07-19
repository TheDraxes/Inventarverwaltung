package GUI.LoginGUI;

import GUI.Dialogs;
import Data.Person;
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
    private UserContainer userContainer = new UserContainer();

    int loginTries = 0;

    private String lastUser = "";

    /**
     * Methode die das Login Fenster Initalisiert und die Nutzerdaten eingelesen.
     *
     * @author Tim
     * @version 1.0
     */

    @FXML
    public void initialize(){
        this.userContainer = userContainer.loadUserData();
        this.userContainer.displayAllUserName();
        System.out.println("[INFO] Login Fenster Initialisiert");
    }

    /**
     *
     * Fängt Keyboard eingaben ab und ruft die loginButtonClicked funktion auf
     * falls die Entertaste gedrückt wurde.
     *
     * @see #loginButtonClicked()
     * @param event
     * @author Tim
     * @version 1.0
     */

    @FXML
    void keyPressed(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            loginButtonClicked();
        }
    }

    /**
     * Methode die aufgerufen wird wenn der Loginbutton geklickt wurde
     *
     * Prüft die eingegebenen Angaben, vergleicht sie mit denen in dem Usercontainer,
     * versteckt das aktuelle Fenster und ruft das Inventarverwaltungsenster auf.
     *
     * @see showStartWindow#showStartWindow(UserContainerAlt, String)
     * @TODO sperrung nach 3 fehlgeschlagenen Loginversuchen
     * @author Tim
     * @version 1.0
     */

    @FXML
    void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(userContainer.checkLogin(username, password)){
            Stage lastWindow = (Stage) loginButton.getScene().getWindow();
            lastWindow.hide();
            new showStartWindow(this.userContainer, userContainer.getPersonByUsername(username));
        } else {
            if(userContainer.userExisting(username) && loginTries == 0){
                lastUser = username;
                loginTries++;
            } else if(username.equals(lastUser)){
                loginTries++;
            } else {
                loginTries = 1;
                lastUser = username;
            }

            if(loginTries < 4){
                Dialogs.warnDialog("Nutzername oder Passwort falsch!", "Passwort " + loginTries + " mal falsch eingegeben!");
            } else {
                Dialogs.warnDialog("Nutzername oder Passwort falsch!", "Account Gesperrt!!");
            }
        }
    }
}
