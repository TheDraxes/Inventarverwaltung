package GUI.StartGUI;

import Data.Abteilung;
import Data.Sachgebiet;
import GUI.Dialogs;
import Data.Person;
import Verwaltung.AssetContainer;
import Verwaltung.OrganisationContainer;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller klasse für das Startfenster welches für die UI der Inventarverwaltung,
 * Benutzerverwaltung (Adminseitig) und dem Ändern des Passwortes für Nutzer zuständig ist
 *
 * @author Tim
 * @version 1.0
 */
public class StartController implements Initializable {
    //Dropdown Menue für die Auswahl des Inventars
    @FXML
    private ComboBox<String> InventarBox;

    //Button für um das ausgewählte Inventar zu bestätigen
    @FXML
    private Button ConfirmButton;

    //Label in dem der Username des eingeloggten benutzers angezeigt wird
    @FXML
    private Label userLabel;

    //Spezielles Menue für Funktionen auf die nur der Admin zugriff hat
    @FXML
    private Menu adminMenue;

    //Container für alle benutzerdaten
    private UserContainer userContainer;

    //Container für Abteilungen und Sachgebiete
    private OrganisationContainer orgContainer;

    //Anzahl der zur verfügung stehen Inventare
    private int inventoryCounter = 0;

    //Der aktuelle speicherpfad in dem Inventare abgespeichert werden
    private String path = "";

    //Array in dem alle Namen der Inventare abgespeichert werden
    private String[] inventories;

    //Benutzerobjekt von dem Momentan eingeloggten Nutzer
    private Person user;

    //Button zum editieren von Organisationen
    @FXML
    private MenuItem editOrgButton;

    @FXML
    private MenuItem delOrgButton;

    /**
     * Initalisierungsmethode die Interaktive Elemente mit Inhalt füllen
     * und Überpfüft ob das Adminmenue angezeigt werden muss
     *
     * @author Tim
     */
    @FXML
    protected void initialize(){
        //Aufsetzen der ComboBox für das StartFenster
        ObservableList<String> _default = FXCollections.observableArrayList();
        File lookUp = new File(path);

        if (lookUp.exists()) {
            File[] fileArray = lookUp.listFiles();
            inventories = new String[fileArray.length];
            inventoryCounter = 0;
            for (int i = 0; i < fileArray.length; i++) {
                if (fileArray[i].getName().endsWith(".Inv")) {
                    _default.add(fileArray[i].getName().substring(0, fileArray[i].getName().length() - 4));
                    inventories[i] = fileArray[i].getName().substring(0, fileArray[i].getName().length() - 4);
                    inventoryCounter++;
                }
            }
            InventarBox.setItems(_default);
            if(inventoryCounter != 0) {
                InventarBox.setValue(_default.get(0));
            } else {
                InventarBox.setValue("Kein Eintrag gefunden!");
            }
        }
        //Anzeigen des Admin menüs
        if(user.isAdmin()){
            adminMenue.setVisible(true);
        } else {
            adminMenue.setVisible(false);
        }

        //laden des Organisazionscontainers
        try {
            orgContainer = orgContainer.loadOrganisationsData();
        } catch (NullPointerException e){
            orgContainer = new OrganisationContainer();
            System.out.println("[FEHLER] Beim Laden des orgContainers");
        }

        if(!orgContainer.anyAbteilungExisting()){
            editOrgButton.setVisible(false);
            delOrgButton.setVisible(false);
        }

        System.out.println("[GUI] Start Fenster Initialisiert");
        System.out.println("[INFO] Speicherpfad: " + path);
    }

    /**
     * Funktion die zur ausführung kommt sobald der Inventar löschen Button gedrückt wurde
     *
     * @param event ->  event das beim Klick ausgelöst wird. Scenebuilder verlangt nach diesem
     *                  Übergabeparameter wird jedoch nich benötigt
     * @author Tim
     * @version 1.0
     */
    @FXML
    protected void deleteInventoryClicked(ActionEvent event) {
        File a = new File(path + "/" + InventarBox.getValue() + ".Inv");
         if(InventarBox.getValue().equals("Kein Eintrag gefunden!")){
          Dialogs.warnDialog("Es wurde noch kein Inventar angelegt!", "Warnung");
          return;
        } else if(!a.exists()){
           Dialogs.warnDialog("Inventar Existiert nicht!", "Warnung");
           return;
         }
        boolean confirmed = Dialogs.confirmDialog(InventarBox.getValue() + " wirklich Löschen?");
        if(confirmed){
            if(a.delete()) {
                inventoryCounter--;
                System.out.println("[INFO]" + "Inventar \"" + InventarBox.getValue() + "\" wurde gelöscht");
            }
            initialize();
        }
    }

    /**
     * Setzt einen neuen Speicherort fest
     *
     * @param event
     */
    @FXML
    protected void newSafeLocation(ActionEvent event) {
        setLookAndFeel();

        File lookUp = new File(path);
        File[] fileArray = lookUp.listFiles();

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

        for(File file : fileArray){
            if(file.getName().endsWith(".Inv")){
                try {
                    File fSrc = new File("" + file); // Quelldatei
                    File fDes = new File(path + "\\" + file.getName()); // Zieldatei
                    FileInputStream fis = new FileInputStream(fSrc); //Stream fuer Quelldatei
                    FileOutputStream fos = new FileOutputStream(fDes); //Stream fuer Zieldatei

                    byte buf[] = new byte[1024]; // Buffer für gelesene Daten
                    while (fis.read(buf) != -1) { // solange lesen, bis EOF
                        fos.write(buf); // Inhalt schreiben
                    }
                    fis.close();
                    fos.flush();
                    fos.close();

                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        initialize();
    }

    /**
     * Funktion zur Parameterübergabe der anderen Controller
     *
     *
     * @param path -> Speicehrpfad
     * @param userContainer -> Container für alle Benutzerdaten
     * @param user -> aktuell eingeloggter user
     */
    public void getParams(String path, UserContainer userContainer, Person user){
        this.userLabel.setText("Eingeloggt als: " + user.getUsername());
        this.path = path;
        this.userContainer = userContainer;
        this.user = user;
        initialize();
    }

    /**
     *
     * Funktion die ein neues Inventar anlegt
     * @param event ->
     * @author Tim
     */
    @FXML
    protected void newInventoryClicked(ActionEvent event) {

        if(!orgContainer.anyAbteilungExisting()){
            Dialogs.warnDialog("Es müssen zunächst Sachgebiete angelegt werden!", "Info");
            return;
        }

        String input = Dialogs.inventoryNameDialog(orgContainer ,"Eingabe", "Inventarnamen Eingeben!");
        if(input == null){
            System.out.println("[INFO] Anlegen abgebrochen");
        } else {
            if (!input.equals("")) {
                boolean alreadyTaken = false;
                for(String inventory : inventories){
                    if(input.equals(inventory)){
                        alreadyTaken = true;
                    }
                }
                if(!alreadyTaken) {
                    String path = this.path + "\\" + input + ".Inv";
                    AssetContainer newContainer = new AssetContainer();

                    newContainer.safeInventar(path);
                    Dialogs.warnDialog("Inventar \"" + input + "\" erfolgreich angelegt!", "Info");
                    System.out.println("[INFO] Inventar '" + input + ".Inv' erstellt");
                    initialize();
                } else {
                    Dialogs.warnDialog("Name bereits Vergeben!", "Warnung");
                }
            } else {
                Dialogs.warnDialog("Keinen Namen Eingegeben!", "Warnung");
            }
        }
    }

    /**
     * Bestätigungsevent für das ausgewählte Inventar
     *
     * das aktuelle fenster wird geschlossen
     * und das neue aufgebaut mit übergabe bestimmter parameter
     *
     * @param event
     * @throws IOException
     * @author Tim
     */
    @FXML
    protected void confirmInventoryClicked(ActionEvent event) throws IOException {
        System.out.println("[INFO] Speicherpfad: " + path);
        if(inventoryCounter != 0) {

            Stage lastWindow = (Stage) ConfirmButton.getScene().getWindow();
            lastWindow.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ViewGUI/ViewStyle.fxml"));
            Parent root = loader.load();

            ViewController controller = loader.getController();
            controller.getParams(InventarBox.getValue(), path, userContainer,user, orgContainer);

            Stage newWindow = new Stage();
            newWindow.setResizable(false);
            newWindow.setTitle(InventarBox.getValue());
            newWindow.setScene(new Scene(root));
            newWindow.show();
        } else {
            Dialogs.warnDialog("Keinen Eintrag ausgewählt!", "Warnung");
        }
    }


    /**
     * Logik für das anlegen eines neuen Benutzers
     * @param event
     * @author Tim
     */
    @FXML
    protected  void newUserClicked(ActionEvent event){
        while (true) {
            String[] userData = buildNewUserWindow();
            if (userData == null) {
                break;
            } else {
               if (!userData[3].equals(userData[4])) {
                    Dialogs.warnDialog("Passwörter stimmen nicht Überein!","Warnung");
                } else if (userData[0].equals("")|| userData[1].equals("") || userData[2].equals("") || userData[3].equals("") || userData[4].equals("") || userData[5].equals("")){
                    Dialogs.warnDialog("Alle Felder müssen ausgefüllt werden!","Warnung");
                } else {
                   boolean men = false;
                   if(userData[2].equals("Männlich")) {
                       men = true;
                   }
                   boolean admin = false;
                   if(userData[5].equals("Ja")){
                       admin = true;
                   }

                    Person newUser = new Person(userData[1],userData[0],men,userData[3], admin);

                    userContainer.insertUser(newUser);
                    Dialogs.warnDialog("Neuen Benutzer erfolgreich angelegt!", "Info");

                    System.out.println("[INFO] neuen Benutzer angelegt");
                    initialize();
                    break;
                }
            }
        }
        userContainer.display();
    }

    /**
     * Aufgerufene Methode beim click auf den Passwort ändern Menüeintrag
     *
     * @author Tim
     */

    @FXML
    protected void editPasswordClicked(){
        while (true) {
            Pair pwPair = Dialogs.changePw(user);
            String errMessage = (String) pwPair.getValue();

            if (pwPair.getValue() != null) {
                if (errMessage.startsWith("[INFO]")) {
                    break;
                }
                System.out.println(errMessage);
            }

            if(pwPair.getKey() != null) {
                userContainer.changePassword(user.getUsername(), (String) pwPair.getKey());
                Dialogs.warnDialog("Passwort erfolgreich geändert!", "Info");
                break;
            }
        }
    }

    /**
     * Funktion um ausgewählte benutzer zu Bearbeiten
     *
     * @author Tim
     */
    @FXML
    protected void editUserClicked(){
        Person choosen = chooseUserWindow();
        if(choosen != null) {
            Person edited = Dialogs.editUserWindow(choosen, user);
            if(edited != null) {
              userContainer.editUser(edited);
              System.out.println("[INFO] Vorgang abgebrochen!");
            }
        } else {
            System.out.println("[INFO] Vorgang abgebrochen!");
        }
    }

    /**
     * Funktion um mehrere ausgewählte nutzer zu löschen
     */
    @FXML
    protected void deleteUser(){
        while (true) {
            Person deleted = chooseUserWindow();
            if (deleted != null) {
                userContainer.deleteUser(deleted.getUsername());
            } else {
                System.out.println("[INFO] Vorgang abgebrochen!");
                break;
            }
        }
    }


    /**
     * Funktion die das Fenster zur auswahl eines Users aufbaut und ein
     * Objekt vom typ Person zurückgibt
     *
     * @return Person -> ausgewählte person z.B zum editieren
     * @author Tim
     */
    private Person chooseUserWindow() {
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle("Benutzer wählen");

        String[] userNames = userContainer.getUserNames();

        ObservableList user = FXCollections.observableArrayList(userNames);

        ComboBox userComboBox= new ComboBox<>(user);

        userComboBox.setValue(this.user.getUsername());


        ButtonType OK_Button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OK_Button, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Nutzer: "),0,0);
        grid.add(userComboBox,1,0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == OK_Button) {
                Person a = userContainer.getPersonByUsername((String) userComboBox.getValue());
                return a;
            } else {
                return null;
            }
        });

        Optional<Person> result = dialog.showAndWait();

        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    /**
     * Funktion die aufgerufen wird wenn der Logout button gedrückt wurde
     *
     * @author Tim
     */
    @FXML
    protected void logoutButtonClicked() {
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

    /**
     * Baut ein Fenster auf in dem die Daten für einen neuen Benutzer eingetragen werden
     *
     * @return
     * @author Tim
     */
    private String[] buildNewUserWindow(){
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
                String[] array = new String[6];
                array[0] = firstName.getText();
                array[1] = secoundName.getText();
                array[2] = (String)gender.getValue();
                array[3] = password.getText();
                array[4] = passwordConfirm.getText();
                array[5] = (String)admin.getValue();
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
    @FXML
    protected void addNewOrganisation(){
        if(userContainer.getNumberOfUser() > 1) {
            int choosen = Dialogs.chooseOrgDialog(orgContainer.anyAbteilungExisting());
            if (choosen < 0) {
                System.out.println("[INFO] Vorgang abgebrochen");
            } else if (choosen == 0) {
                //Abteilung
                Pair<Abteilung,String> result = Dialogs.newAbteilungWindow(userContainer, null);
                if(result == null){
                    return;
                }
                if(result.getValue() != null){
                    Dialogs.warnDialog(result.getValue(),"Warnung");
                    return;
                } else {
                    orgContainer.insertAbteilung(result.getKey());
                }
            } else if (choosen == 1) {
                Pair<Sachgebiet,String> result = Dialogs.newSachgebietWindow(orgContainer,userContainer);
                if(result == null){
                    return;
                }
                if(result.getValue() != null && result.getKey() == null){
                    Dialogs.warnDialog(result.getValue(),"Warnung");
                    return;
                } else {
                    orgContainer.insertSachgebiet(result.getKey(), result.getValue());
                }
            }
            orgContainer.safeOrganisationsData();
        } else {
            Dialogs.warnDialog("Sie müssen zunächst einen Benutzer anlegen der als Leiter der Organisation eingestellt werden kann", "INFO");
        }
    }
    @FXML
    public void editOrganisattion() {
        int choose = Dialogs.chooseOrgDialog(orgContainer.anyAbteilungExisting());
        if (choose < 0) {
            System.out.println("[INFO] Vorgang abgebrochen");
        } else if (choose == 0) {
            //Abteilung
            String abt = Dialogs.chooseOrg(orgContainer, "Organisation auswählen", "Org Wählen");
            if (abt == null) {
                return;
            } else {
                Pair<Abteilung,String> result = Dialogs.newAbteilungWindow(userContainer,orgContainer.getAbteilungByKürzel(abt));
                if(result.getValue() != null && result.getKey() == null){
                    Dialogs.warnDialog(result.getValue(),"Warnung");
                    return;
                } else {
                    orgContainer.editAbteilung(result.getKey());
                }
            }
        } else if (choose == 1) {
            //Sachgebiet
            if(orgContainer.anySachgebietExisting()){

            } else {
                return;
            }
        }
    }
    /**
     * setzt das Look and Feel für Swing elemente
     *      -> müsste noch durch Javafx ersetzt werden
     */
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
