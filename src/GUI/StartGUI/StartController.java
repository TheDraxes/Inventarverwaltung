package GUI.StartGUI;

import GUI.ViewGUI.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

/*
    Controller Klasse für das Start Fenster

    Hier werden Methoden geschrieben die durch Interaktion mit dem StartGUI getriggered werden

    Der Style des Start Fenster ist in der FXML Datei "StartStyle" Implementiert (SceneBuilder Tool)
*/
public class StartController {
    //Dropdown Menue für die Auswahl des Inventars
    @FXML
    private ComboBox<String> InventarBox;
    //Button um ein neues Inventar anzulegen(Noch ohne Funktion)
    @FXML
    private Button newButton;
    //Button zum bestätigen des Ausgewählten Inventars (Noch öffnet sich nur das ViewFenster mit Platzhaltereinträgen)
    @FXML
    private Button ConfirmButton;

    //Funktion die die werte des AuswahlDropdowns festlegt
    @FXML
    void ComboBoxInit() {
        ObservableList<String> _default = FXCollections.observableArrayList("DVZ-Inventar", "Haushalts-Inventar");
        InventarBox.setItems(_default);
    }

    //Methode die aufgerufen wird wenn der newButton gedrückt wird
    @FXML
    void newClicked(ActionEvent event) {
        System.out.println("'Neues Inventar' Button wurde gedrückt!");
    }

    //Methode die aufgerufen wird wenn der ConfirmButton gedrückt wird
    //Versteckt das Aktuelle Fenster und öffnet das neue Fenster
    @FXML
    void confirmClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage) ConfirmButton.getScene().getWindow();
        stage.hide();

        new View();
    }

}
