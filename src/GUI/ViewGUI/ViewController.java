package GUI.ViewGUI;

import Verwaltung.UserContainer;
import GUI.StartGUI.StartController;
import Item.BodenUndGebaeude;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
    Controller Klasse für das View Fenster

    Hier werden Methoden geschrieben die durch Interaktion mit dem ViewGUI getriggered werden

    Der Style des Start Fenster ist in der FXML Datei "ViewStyle" Implementiert (SceneBuilder Tool)
*/

public class ViewController implements Initializable {

    //ScrollPane für die Inventar einträge. Hier müssen nur die ItemEntry Objekte angeheftet werden
    @FXML
    private ScrollPane ItemScrollPane;

    @FXML
    private Label nameLabel;

    UserContainer userContainer;

    private String path;

    @FXML
    public void initialize(){
        VBox test = new VBox();
        test.setSpacing(5);
        Separator VerticalLine = new Separator();
        VerticalLine.setOrientation(Orientation.VERTICAL);
        VerticalLine.setMaxWidth(500);
        for(int i = 0; i <= 20; i++) {
            test.getChildren().addAll(new ItemEntry(new BodenUndGebaeude()));
        }
        ItemScrollPane.setContent(test);
        System.out.println("**View Fenster Initialisiert");
        System.out.println("**Speicherpfad: " + path);
    }

    //Methode die ausgeführt wird wenn der "Inventar anzeigen" Button in der Menueleiste Gedrückt wird
    //Hier werden an das ScrollPane Beispielhaft Platzhalter angehängt
    @FXML
    void ShowClicked(ActionEvent event) {
        initialize();
    }

    public void getParams(String text, String path, UserContainer userContainer){
        nameLabel.setText(text);
        this.path = path;
        this.userContainer = userContainer;
        initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void backClicked(ActionEvent event){

        Stage lastWindow = (Stage) nameLabel.getScene().getWindow();
        lastWindow.hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/StartGUI/StartStyle.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StartController controller = loader.getController();
        controller.getPath(path);
        controller.getUserContainer(this.userContainer);

        
        Stage stage = new Stage();
        stage.setTitle("Inventarverwaltung 1.0");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
