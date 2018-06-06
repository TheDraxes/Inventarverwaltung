package GUI.StartGUI;

import GUI.ViewGUI.ViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/*
    Controller Klasse für das Start Fenster

    Hier werden Methoden geschrieben die durch Interaktion mit dem StartGUI getriggered werden

    Der Style des Start Fenster ist in der FXML Datei "StartStyle" Implementiert (SceneBuilder Tool)
*/
public class StartController implements Initializable {
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
    private Tooltip Tooltip;

    @FXML
    private Button deleteButton;

    private int anz = 0;

    @FXML
    private String path = "";

    //Funktion die die werte des AuswahlDropdowns festlegt
    @FXML
    public void initialize(){

            ObservableList<String> _default = FXCollections.observableArrayList();
            File lookUp = new File(path);

            if (lookUp.exists()) {
                File[] fileArray = lookUp.listFiles();
                anz = 0;
                for (int i = 0; i < fileArray.length; i++) {
                    if (fileArray[i].getName().endsWith(".Inv")) {
                        _default.add(fileArray[i].getName().substring(0, fileArray[i].getName().length() - 4));
                        anz++;
                    }
                }
                InventarBox.setItems(_default);
                if(anz != 0) {
                    InventarBox.setValue(_default.get(0));
                } else {
                    InventarBox.setValue("Kein Eintrag gefunden!");
                }
            }
            System.out.println("Anzahl: " + anz);
    }

    @FXML
    void deleteClicked(ActionEvent event) {
        InventarBox.getValue();
        File a = new File(path + "/" + InventarBox.getValue() + ".Inv");
        if(a.exists()){
            a.delete();
            anz--;
            System.out.println(InventarBox.getValue() + " wurde gelöscht");
            initialize();
        }
    }

    @FXML
    void newSafeLocation(ActionEvent event) {
        setLookAndFeel();

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Speicherpfad für die Inventarverwaltung");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);
        File f;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            path = f.getPath();
        }
        try {
            FileOutputStream outputStream = new FileOutputStream("startUp.dat");
            ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);
            objectOut.writeObject(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        initialize();
    }

    public void getPath(String path){
        this.path = path;
        initialize();
    }

    //Methode die aufgerufen wird wenn der newButton gedrückt wird
    @FXML
    void newClicked(ActionEvent event) {

        String input = askForName();

        if(input == null){
            System.out.println("abgebrochen");
        } else {
            if (!input.equals("")) {
                File newFile = new File(path + "/" + input + ".Inv");
                try {
                    newFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                initialize();
            } else {
                JOptionPane.showMessageDialog(null, "Keinen Namen Eingegeben!");
            }
        }
    }

    //Methode die aufgerufen wird wenn der ConfirmButton gedrückt wird
    //Versteckt das Aktuelle Fenster und öffnet das neue Fenster
    @FXML
    void confirmClicked(ActionEvent event) throws IOException {
        if(anz != 0) {

            Stage lastWindow = (Stage) ConfirmButton.getScene().getWindow();
            lastWindow.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ViewGUI/ViewStyle.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.getParams(InventarBox.getValue(),path);

            Stage newWindow = new Stage();
            newWindow.setScene(new Scene(root));
            newWindow.show();
        } else {
            setLookAndFeel();
            JOptionPane.showMessageDialog(null,"Keinen Eintrag ausgewählt");
        }

    }
    protected String askForName(){
        setLookAndFeel();
        return JOptionPane.showInputDialog("Namen der Datenbank eingeben!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void setLookAndFeel(){
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
