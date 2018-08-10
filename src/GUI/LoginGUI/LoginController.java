package GUI.LoginGUI;

import GUI.Dialogs;
import GUI.StartGUI.initStartWindow;
import Verwaltung.UserContainer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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

    //Anzahl Fehlgeschlagener Loginversuche
    private int loginTries = 0;

    //Username vom letzten anmeldeversuch
    private String lastUser = "";

    /**
     * Methode die das Login Fenster Initalisiert und die Nutzerdaten eingelesen.
     *
     * @author Tim
     */

    @FXML
    protected void initialize(){
        this.userContainer = userContainer.loadUserData();
        this.userContainer.displayAllUserName();
        System.out.println("[GUI] Login Fenster Initialisiert");
    }

    /**
     *
     * Fängt Keyboard eingaben ab und ruft die loginButtonClicked funktion auf
     * falls die Entertaste gedrückt wurde.
     *
     * @see #loginButtonClicked()
     * @param event event das beim tastendruck gefeuert wird
     * @author Tim
     */

    @FXML
    protected void keyBoardPressed(KeyEvent event){
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
     * @author Tim
     */

    @FXML
    protected void loginButtonClicked() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if(userContainer.userExisting(username) && userContainer.isBlocked(username)){
            Dialogs.warnDialog("Dieser Benutzer wurde geblockt und muss vom Admin entsperrt werden", "Warnung");
            return;
        }

        if(userContainer.checkLogin(username, password)){
            Stage lastWindow = (Stage) loginButton.getScene().getWindow();
            lastWindow.hide();
            new initStartWindow(this.userContainer, userContainer.getPersonByUsername(username));
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
                userContainer.blockUser(username);
            }
        }
    }
}
