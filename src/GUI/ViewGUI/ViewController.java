package GUI.ViewGUI;

import data.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

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

    //Methode die ausgeführt wird wenn der "Inventar anzeigen" Button in der Menueleiste Gedrückt wird
    //Hier werden an das ScrollPane Beispielhaft Platzhalter angehängt
    @FXML
    void ShowClicked(ActionEvent event) {
        VBox test = new VBox();
        test.setSpacing(5);
        Separator VerticalLine = new Separator();
        VerticalLine.setOrientation(Orientation.VERTICAL);
        VerticalLine.setMaxWidth(500);
        for(int i = 0; i <= 20; i++) {
            test.getChildren().addAll(new ItemEntry(new Item("Platzhalter", (double) i)));
        }
        ItemScrollPane.setContent(test);
    }

    public void getText(String text){
        nameLabel.setText(text);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
