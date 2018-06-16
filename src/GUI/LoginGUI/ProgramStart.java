package GUI.LoginGUI;

import GUI.StartGUI.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

/**
 * Klasse die als einstiegspunkt für die Application dient.
 *
 * Sie baut das Initale Loginfenster auf
 *
 * @author Tim
 * @version 1.0
 */
public class ProgramStart extends Application {

    public void ProgramStart() {
        launch();
    }

    /**
     * Methode aus dem Application Interface
     *
     * @param primaryStage Stage für das Loginfenster
     * @throws Exception ClassNotFoundException
     */
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginGUI/LoginStyle.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Inventarverwaltung 1.0");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }
}

