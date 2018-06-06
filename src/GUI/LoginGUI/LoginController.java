package GUI.LoginGUI;

import GUI.StartGUI.showStartWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private String[][] User;

    @FXML
    void loginButtonClicked(ActionEvent event) {
        System.out.println(usernameField.getText());
        System.out.println(passwordField.getText());
        if(usernameField.getText().equals("Admin") && passwordField.getText().equals("1234")) {
            Stage lastWindow = (Stage) loginButton.getScene().getWindow();
            lastWindow.hide();
            new showStartWindow();
        }
    }
}
