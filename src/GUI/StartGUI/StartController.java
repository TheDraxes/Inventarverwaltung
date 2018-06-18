package GUI.StartGUI;

import GUI.Dialogs;
import Data.Person;
import Verwaltung.ItemContainer;
import Verwaltung.UserContainer;
import GUI.ViewGUI.ViewController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
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

    //Button zum bestätigen des Ausgewählten Inventars (Noch öffnet sich nur das ViewFenster mit Platzhaltereinträgen)
    @FXML
    private Button ConfirmButton;

    @FXML
    private Label userLabel;

    @FXML
    private Menu adminMenue;

    @FXML
    private Tooltip Tooltip;

    private UserContainer userContainer;

    private int anz = 0;

    private String path = "";

    private String user = "";

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

        if(user.equals("admin")){
            adminMenue.setVisible(true);
        } else {
            adminMenue.setVisible(false);
        }
        System.out.println("**Start Fenster Initialisiert");
    }

    @FXML
    void deleteClicked(ActionEvent event) {
        File a = new File(path + "/" + InventarBox.getValue() + ".Inv");
        boolean confirmed = Dialogs.confirmDialog(InventarBox.getValue() + " wirklich Löschen?");
        if(confirmed && a.exists()){
            System.out.println(a.getPath());
            a.delete();
            anz--;
            System.out.println("**" + "Inventar \"" + InventarBox.getValue() + "\" wurde gelöscht");
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

    public void getParams(String path, UserContainer userContainer, String user){
        this.userLabel.setText("Eingeloggt als: " + user);
        this.path = path;
        this.userContainer = userContainer;
        this.user = user;
        initialize();
    }

    //Methode die aufgerufen wird wenn der newButton gedrückt wird
    @FXML
    void newClicked(ActionEvent event) {

        String input = Dialogs.inputDialog("Inventar","Eingabe", "Inventarnamen Eingeben!");

        if(input == null){
            System.out.println("**Anlgen abgebrochen abgebrochen");
        } else {
            if (!input.equals("")) {
                File newFile = new File(path + "\\" + input + ".Inv");
                ItemContainer newContainer = new ItemContainer();
                try {
                    FileOutputStream outputStream = new FileOutputStream(newFile);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(newContainer);
                    System.out.println("**Inventar \"" + newFile.getName() + "\"" +
                            " erstellt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                initialize();
            } else {
                Dialogs.warnDialog("Keinen Namen Eingegeben!", "Warnung");
            }
        }
    }

    //Methode die aufgerufen wird wenn der ConfirmButton gedrückt wird
    //Versteckt das Aktuelle Fenster und öffnet das neue Fenster
    @FXML
    void confirmClicked(ActionEvent event) throws IOException {
        System.out.println("**Speicherpfad: " + path);
        if(anz != 0) {

            Stage lastWindow = (Stage) ConfirmButton.getScene().getWindow();
            lastWindow.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ViewGUI/ViewStyle.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.getParams(InventarBox.getValue(), path, userContainer,user);

            Stage newWindow = new Stage();
            newWindow.setResizable(false);
            newWindow.setTitle(InventarBox.getValue());
            newWindow.setScene(new Scene(root));
            newWindow.show();
        } else {
            Dialogs.warnDialog("Keinen Eintrag ausgewählt!", "Warnung");
        }

    }


    @FXML
    void newUserClicked(ActionEvent event){
        while (true) {
            String[] a = buildNewUserWindow();
            if (a == null) {
                break;
            } else {
                if (userContainer.userIsDuplicate(a[0])) {
                    Dialogs.warnDialog("Username Bereits belegt", "Warnung");
                } else if (!a[1].equals(a[2])) {
                    Dialogs.warnDialog("Passwörter stimmen nicht Überein!","Warnung");
                } else if (a[0].equals("")|| a[1].equals("") || a[2].equals(3)){
                    Dialogs.warnDialog("Mit * markierte Felder müssen ausgefüllt werden","Warnung");
                } else {
                    Person newUser = new Person();
                    newUser.setBenutzername(a[0]);
                    newUser.setPasswort(a[1]);

                    userContainer.insertUser(newUser);

                    System.out.println("**neuen Benutzer angelegt");
                    initialize();
                    break;
                }
            }
        }
    }
    @FXML
    public void deleteUser(){
        String[] deletedUsers = buildDeleteUserWindow();
        System.out.println("** User ausgewählt: " + Arrays.toString(deletedUsers));
        if(deletedUsers != null){
            if(deletedUsers.length > 0) {
                for (int i = 0; i < deletedUsers.length; i++) {
                    if (!deletedUsers[i].equals("admin")) {
                        userContainer.deleteUser(deletedUsers[i]);
                    } else {
                        Dialogs.warnDialog("Der Admin Account kann nicht gelöscht werden!", "Warnung");
                    }
                }
            }
        } else {
            System.out.println("**Vorgang abgebrochen!");
        }
    }

    @FXML
    public void logoutButtonClicked() {
        Stage lastWindow = (Stage) userLabel.getScene().getWindow();
        lastWindow.hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/LoginGUI/LoginStyle.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setTitle("Inventarverwaltung 1.0");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public String[] buildDeleteUserWindow(){
        int number = userContainer.getNumberOfUser();
        CheckBox[] checkBoxes = new CheckBox[number];

        String[] usernames = userContainer.getUserNames();

        for (int i = 0; i < number; i++) {
            checkBoxes[i] = new CheckBox(usernames[i]);
        }

        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Benutzer Löschen");

        ButtonType OK_Button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OK_Button, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        for (int i = 0; i < number; i++) {
            grid.add(checkBoxes[i], 0, i);
        }

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == OK_Button){
                int anz = 0;
                for(int i = 0; i < number; i++){
                    if(checkBoxes[i].isSelected()) {
                        anz++;
                    }
                }
                String[] array = new String[anz];
                anz = 0;
                for(int i = 0; i < number; i++){
                    if(checkBoxes[i].isSelected()){
                        array[anz] = checkBoxes[i].getText();
                        anz++;
                    }
                }
                return array;
            } else if(dialogButton == ButtonType.CANCEL){
                return null;
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();

        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public String[] buildNewUserWindow(){
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("Neuer Benutzer");
        ButtonType addButton = new ButtonType("Hinzufügen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField firstName = new TextField();
        firstName.setPromptText("Vorname");
        TextField secoundName = new TextField();
        secoundName.setPromptText("Nachname");

        ObservableList<String> genderOptions =
                FXCollections.observableArrayList(
                        "Männlich",
                        "Weiblich"
                );
        ComboBox gender = new ComboBox(genderOptions);
        gender.setValue("Weiblich");

        PasswordField password = new PasswordField();
        password.setPromptText("Passwort");
        PasswordField passwordConfirm = new PasswordField();
        passwordConfirm.setPromptText("Passwort bestätigen");

        ObservableList<String> adminOptions =
                FXCollections.observableArrayList(
                        "Ja",
                        "Nein"
                );
        ComboBox admin = new ComboBox(adminOptions);
        admin.setValue("Nein");

        grid.add(new Label("Vorname: "), 0, 0);
        grid.add(firstName,1,0);
        grid.add(new Label("Nachname: "), 0, 1);
        grid.add(secoundName,1,1);
        grid.add(new Label("Geschlecht: "), 0, 2);
        grid.add(gender,1,2);
        grid.add(new Label("Passwort: "), 0, 3);
        grid.add(password,1,3);
        grid.add(new Label("Passwort bestätigen: "), 0, 4);
        grid.add(passwordConfirm,1,4);

        grid.add(new Label("Admin: "), 0, 5);
        grid.add(admin,1,5);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> firstName.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == addButton){
                String[] array = new String[3];
                array[0] = secoundName.getText();
                array[1] = password.getText();
                array[2] = passwordConfirm.getText();
                return array;
            } else if(dialogButton == ButtonType.CANCEL){
                return null;
            }
            return null;
        });

        Optional<String[]> result = dialog.showAndWait();
        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
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
