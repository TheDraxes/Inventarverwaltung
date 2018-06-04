package GUI.StartGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
    Klasse die sich darum kümmert das im Scene Builder erstellte StartFenster Über die StartStyle.fxml einzulesen
    und aufzubauen.
*/
public class ShowStart extends Application {

    public void showStart() {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("StartStyle.fxml"));
        primaryStage.setTitle("Inventarverwaltung 1.0");
        Scene a = new Scene(root);
        primaryStage.setScene(a);
        primaryStage.show();

    }
}
