package GUI.ViewGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends Stage {
    Stage stage;
    public  View() throws IOException {
        stage = this;
        Parent root = FXMLLoader.load(getClass().getResource("ViewStyle.fxml"));
        stage.setTitle("Inventarverwaltung 1.0");
        Scene a = new Scene(root);
        stage.setScene(a);
        stage.show();
    }


}
