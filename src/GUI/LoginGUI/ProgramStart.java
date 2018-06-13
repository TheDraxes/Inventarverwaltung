package GUI.LoginGUI;

import GUI.StartGUI.StartController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

/*
    Klasse die sich darum kümmert das im Scene Builder erstellte StartFenster Über die StartStyle.fxml einzulesen
    und aufzubauen.
*/
public class ProgramStart extends Application {

    public void ProgramStart() {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginGUI/LoginStyle.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setTitle("Inventarverwaltung 1.0");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        /*
        Parent root = FXMLLoader.load(getClass().getResource("StartStyle.fxml"));
        primaryStage.setTitle("Inventarverwaltung 1.0");
        Scene a = new Scene(root);
        primaryStage.setScene(a);
        primaryStage.show();
        */
    }
}

