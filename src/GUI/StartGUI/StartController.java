package GUI.StartGUI;

import GUI.ViewGUI.View;
import GUI.ViewGUI.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
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

    @FXML
    private Button deleteButton;

    //Funktion die die werte des AuswahlDropdowns festlegt
    @FXML
    public void initialize(){
        ObservableList<String> _default = FXCollections.observableArrayList();
        File lookUp = new File("C:/projekte/Inventarverwaltung/src/SafedInv");
        if(lookUp.exists()){
            System.out.println("Pfad Existiert");
        }

        File[] fileArray = lookUp.listFiles();
        for(int i = 0; i < fileArray.length; i++){
            if(fileArray[i].getName().endsWith(".Inv")){
                _default.add(fileArray[i].getName().substring(0,fileArray[i].getName().length()-4));
            }
        }

        InventarBox.setItems(_default);
        InventarBox.setValue(_default.get(0));
    }

    @FXML
    void deleteClicked(ActionEvent event) {

    }

    @FXML
    void confirmNewInv(ActionEvent event) {
        System.out.println("Test");
    }

    //Methode die aufgerufen wird wenn der newButton gedrückt wird
    @FXML
    void newClicked(ActionEvent event) {
        InputDialog newInput = new InputDialog();
        newInput.setLocationRelativeTo(null);
        newInput.pack();
        newInput.setSize(300, 100);
        newInput.setVisible(true);

        /*
        System.out.println("'Neues Inventar' Button wurde gedrückt!");

        String eingabe = (String) JOptionPane.showInputDialog("Namen der Datenbank eingeben!");
        System.out.println(eingabe);

        File newFile = new File("C:/Users/Tim/Projekte/Inventarverwaltung/src/SafedInv/"+eingabe+".Inv");

        try {
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        initialize();
    }

    //Methode die aufgerufen wird wenn der ConfirmButton gedrückt wird
    //Versteckt das Aktuelle Fenster und öffnet das neue Fenster
    @FXML
    void confirmClicked(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ViewGUI/ViewStyle.fxml"));
        Parent root = (Parent) loader.load();

        ViewController controller = loader.getController();
        controller.getText(InventarBox.getValue());

        Stage stage = (Stage) ConfirmButton.getScene().getWindow();
        stage.hide();

        Stage newWindow = new Stage();
        newWindow.setScene(new Scene(root));
        newWindow.show();


        /*
        new View();
        */
    }

}
