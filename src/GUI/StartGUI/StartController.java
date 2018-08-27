package GUI.StartGUI;

import Data.Abteilung;
import Data.Organisation;
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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static GUI.Dialogs.inventoryNameDialog;

/**
 * Controller klasse für das Startfenster welches für die UI der Inventarverwaltung,
 * Benutzerverwaltung (Adminseitig) und dem Ändern des Passwortes für Nutzer zuständig ist
 *
 *
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
    private OrganisationContainer orgContainer = new OrganisationContainer();

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
    private MenuItem orgEdit;

    @FXML
    private MenuItem orgDelete;

    @FXML
    private MenuItem orgCreate;

    @FXML
    private Button editInvName;



    /**
     * Initalisierungsmethode die Interaktive Elemente mit Inhalt füllen
     * und Überpfüft ob das Adminmenue angezeigt werden muss
     *
     *
     * @see OrganisationContainer#loadOrganisationsData()
     */
    @FXML
    protected void initialize(){

        File lookUp = new File(path);
        if(!lookUp.exists()){
            Dialogs.warnDialog("Der Angegebene Speicherpfad existiert nicht mehr. Sie müssen einen neuen Speicherpfad wählen!", "Warnung");
            askForPath();
        }

        fillInventoryBox();
        //Anzeigen des Admin menüs
        if(user.isAdmin()){
            adminMenue.setVisible(true);
            orgCreate.setVisible(true);
            orgDelete.setVisible(true);
            orgEdit.setVisible(true);
        } else {
            adminMenue.setVisible(false);
            orgCreate.setVisible(false);
            orgDelete.setVisible(false);
            orgEdit.setVisible(false);
        }

        //laden des Organisazionscontainers

        try {
            orgContainer = orgContainer.loadOrganisationsData();

            if (!orgContainer.anyAbteilungExisting()) {
                orgEdit.setVisible(false);
                orgDelete.setVisible(false);
            }

            for (String a : orgContainer.getAllAbteilungsKuerzel()) {
                System.out.println(a);
            }

        } catch (NullPointerException e) {
            System.out.println("[FEHLER] Beim Laden des orgContainers");
            orgEdit.setVisible(false);
            orgDelete.setVisible(false);
        }

        System.out.println("[GUI] Start Fenster Initialisiert");
        System.out.println("[INFO] Speicherpfad: " + path);
    }


    private void fillInventoryBox(){
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
                editInvName.setVisible(false);
            }
        }
    }

    /**
     * Funktion die zur ausführung kommt sobald der Inventar löschen Button gedrückt wurde
     *
     *
     * @see Dialogs#warnDialog(String, String)
     */
    @FXML
    protected void deleteInventoryClicked() {
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
                Dialogs.warnDialog("Das ausgewählte Inventar wurde erfolgreich gelöscht!", "[Info]");
                System.out.println("[INFO]" + "Inventar \"" + InventarBox.getValue() + "\" wurde gelöscht");
            }
            initialize();
        }
    }

    private void askForPath(){
        setLookAndFeel();
        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Speicherpfad für die Inventarverwaltung");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);
        File f;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            if(!f.exists()){
                Stage lastWindow = (Stage) ConfirmButton.getScene().getWindow();
                lastWindow.hide();
            }
            path = f.getPath();

            try {
                FileOutputStream outputStream = new FileOutputStream("startUp.dat");
                ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);
                objectOut.writeObject(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

    @FXML
    private void checkForEdit(){

        try{
            if (InventarBox.getValue().equals("Kein Eintrag gefunden!")) {
                editInvName.setVisible(false);
            } else {
                editInvName.setVisible(true);
            }
        } catch (Exception e){

        }
    }

    /**
     * Setzt einen neuen Speicherort fest
     *
     * @see JFileChooser
     */
    @FXML
    protected void newSafeLocation() {
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

        for (File file : fileArray) {
            if (file.getName().endsWith(".Inv")) {
                try {
                    File fSrc = new File("" + file); // Quelldatei
                    File fDes = new File(path + "\\" + file.getName()); // Zieldatei
                    FileInputStream fis = new FileInputStream(fSrc); //Stream fuer Quelldatei
                    FileOutputStream fos = new FileOutputStream(fDes); //Stream fuer Zieldatei

                    byte buf[] = new byte[1024]; // Buffer für gelesene Daten
                    while (fis.read(buf) != -1) { // solange lesen, bis EOF(End of File)
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
     * @param path Speicehrpfad
     * @param userContainer Container für alle Benutzerdaten
     * @param user aktuell eingeloggter user
     * @see #initialize()
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
     *
     * @see AssetContainer
     * @see Dialogs#inventoryNameDialog(OrganisationContainer, String, String, String)
     */
    @FXML
    protected void newInventoryClicked() {

        if(orgContainer == null){
            orgContainer =  new OrganisationContainer();
        }

        if(!orgContainer.anySachgebietExisting()){
            Dialogs.warnDialog("Es müssen zunächst Sachgebiete angelegt werden!", "Info");
            return;
        }
        if(orgContainer.anySachgebietExisting()){
            String input = inventoryNameDialog(orgContainer ,"Eingabe", "Inventarnamen eingeben!", null);
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

                        if(!newContainer.safeInventar(path)){
                            Dialogs.warnDialog("Der Speicherpfad scheint nicht mehr zu existieren. Sie müssen einen neuen Auswählen!", "Warnung");
                            askForPath();
                            return;
                        }
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
        } else {
            Dialogs.warnDialog("Es wurde noch kein Sachgebiet angelegt!", "Info");
        }
    }

    /**
     * Bestätigungsevent für das ausgewählte Inventar
     *
     * das aktuelle fenster wird geschlossen
     * und das neue aufgebaut mit übergabe bestimmter parameter
     *
     *
     */
    @FXML
    protected void confirmInventoryClicked(){
        System.out.println("[INFO] Speicherpfad: " + path);

        if(!new File(path).exists()){
            Dialogs.warnDialog("Der Speicherpfad scheint nicht mehr zu existieren!", "Warnung");
            return;
        }

        if(inventoryCounter != 0) {
            Stage lastWindow = (Stage) ConfirmButton.getScene().getWindow();
            lastWindow.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/ViewGUI/ViewStyle.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                System.out.println("[FEHLER] beim laden der FXML datei");
                e.printStackTrace();
            }

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
     *
     */
    @FXML
    protected void newUserClicked(){
        while (true) {
            String[] userData = buildNewUserWindow();
            if (userData == null) {
                break;
            } else {
               if (!userData[3].equals(userData[4])) {
                    Dialogs.warnDialog("Passwörter stimmen nicht Überein!","Warnung");
               } else if (userData[0].equals("")||
                       userData[1].equals("") ||
                       userData[2].equals("") ||
                       userData[3].equals("") ||
                       userData[4].equals("") ||
                       userData[5].equals("")
                       )
               {
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
     *
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
                Dialogs.warnDialog(errMessage, "Info");
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
     *
     */
    @FXML
    protected void editUserClicked(){
        Person choosen = chooseUserWindow();
        if(choosen != null) {
            Person edited = Dialogs.editUserWindow(choosen, user);
            if(edited != null) {
              userContainer.editUser(edited);
              Dialogs.warnDialog("Benutzer erfolgreich editiert!", "Info");
              System.out.println("[INFO] Vorgang abgebrochen!");
              userContainer.safeUserData();
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
                if(deleted.getUsername().equals(user.getUsername())){
                    Dialogs.warnDialog("Der aktuell eingeloggte Nutzer darf nicht gelöscht werden", "Info");
                    continue;
                }
                if(deleted.getUsername().equals("admin")){
                  Dialogs.warnDialog("Der Admin Account kann nicht gelöscht werden!","Warnung");
                  return;
                }
                if(Dialogs.confirmDialog("Benutzer " + deleted.getUsername() + " wirklich löschen?")) {
                  userContainer.deleteUser(deleted.getUsername());
                  Dialogs.warnDialog("Benutzer erfolgreich gelöscht!", "Info");
                  return;
                }
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
     * @return Person ausgewählte person z.B zum editieren
     *
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
              return userContainer.getPersonByUsername((String) userComboBox.getValue());
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
     *
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
     * @return array in dem die Daten des neuen Benutzers gespeichert werden
     *
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

    /**
     * Logik für das anlegen einer Neuen Organisation
     *
     *
     */
    @FXML
    protected void addNewOrganisation(){
        if(orgContainer == null){
            orgContainer = new OrganisationContainer();
        }
        if(userContainer.getNumberOfUser() > 1) {
            int choosen = 0;
            if(orgContainer != null) {
                choosen = Dialogs.chooseOrgDialog(orgContainer.anySachgebietExisting(), orgContainer.anyAbteilungExisting(), "angelegt");
            } else {
                choosen = Dialogs.chooseOrgDialog(false,orgContainer.anyAbteilungExisting(),  "angelegt");
            }
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
                    if(orgContainer.existingAbteilungName(result.getKey().getName()))
                    {
                        Dialogs.warnDialog("Abteilungsname bereits vorhanden", "Warnung");
                        return;
                    }
                    if(orgContainer.existingAbteilungKuerzel(result.getKey().getKuerzel())){
                        Dialogs.warnDialog("Abteilungskuerzel bereits vorhanden", "Warnung");
                        return;
                    }
                    orgContainer.insertAbteilung(result.getKey());
                    orgEdit.setVisible(true);
                    orgDelete.setVisible(true);
                }
            } else if (choosen == 1) {
                Pair<Sachgebiet,String> result = Dialogs.newSachgebietWindow(orgContainer, userContainer, null);
                if(result == null){
                    System.out.println("Result ist null!");
                    return;
                }
                if(result.getValue() != null && result.getKey() == null){
                    Dialogs.warnDialog(result.getValue(),"Warnung");
                    return;
                } else {
                    if(orgContainer.sachgebietNameExisting(result.getKey().getName())) {
                        Dialogs.warnDialog("Sachgebietsname bereits vorhanden", "Warnung");
                        return;
                    }
                    if(orgContainer.sachgebietKuerzelExisting(result.getKey().getKuerzel())){
                        Dialogs.warnDialog("Sachgebietskuerzel bereits vorhanden", "Warnung");
                        return;
                    }
                    orgContainer.insertSachgebiet(result.getKey(), result.getValue());
                }
            }
            orgContainer.safeOrganisationsData();
            initialize();
        } else {
            Dialogs.warnDialog("Sie müssen zunächst einen Benutzer anlegen der als Leiter der Organisation eingestellt werden kann", "Info");
        }
    }

    @FXML
    public void editInventoryName(){
        String oldName = InventarBox.getValue();
        String newName = Dialogs.inventoryNameDialog(orgContainer, "Bearbeiten", "Neuen Namen vergeben!", oldName);
        if(newName == null){
            return;
        }
        boolean alreadyTaken = false;
        for(String inventory : inventories){
            if(newName.equals(inventory)){
                alreadyTaken = true;
            }
        }
        if(!alreadyTaken) {
            orgContainer.renameInventar(path, newName + ".Inv", oldName + ".Inv");
            initialize();
        } else {
            Dialogs.warnDialog("Inventarname bereits vergeben", "Warnung");
        }
    }

    /**
     * Gibt eine Tabelle aller Abteilungen und Sachgebiete mit ihren Namen aus
     */
    @FXML
    public void orgSummaryClicked(){
      ObservableList<Organisation> list = FXCollections.observableArrayList(orgContainer.getAbteilungArrayList());
      ObservableList<Organisation> sachList = FXCollections.observableArrayList(orgContainer.getAllSachgebiete());

      TableView<Organisation> sachgebietTable = new TableView();
      sachgebietTable.setPlaceholder(new Label("Keine Sachgebiete angelegt!"));

      TableColumn nameSach = new TableColumn("Sachgebiet");
      nameSach.setPrefWidth(188);
      nameSach.setCellValueFactory(new PropertyValueFactory<>("name"));


      TableColumn kuerzelSach = new TableColumn("Kürzel");
      kuerzelSach.setPrefWidth(100);
      kuerzelSach.setCellValueFactory(new PropertyValueFactory<>("kuerzel"));


      sachgebietTable.getColumns().addAll(nameSach,kuerzelSach);
      sachgebietTable.setItems(sachList);


      TableView<Organisation> abteilungTable = new TableView();
      abteilungTable.setPlaceholder(new Label("Keine Abteilungen angelegt!"));

      TableColumn nameAbt = new TableColumn("Abteilung");
      nameAbt.setPrefWidth(188);
      nameAbt.setCellValueFactory(new PropertyValueFactory<>("name"));

      TableColumn kuerzelAbt = new TableColumn("Kürzel");
      kuerzelAbt.setPrefWidth(100);
      kuerzelAbt.setCellValueFactory(new PropertyValueFactory<>("kuerzel"));

      abteilungTable.getColumns().addAll(nameAbt,kuerzelAbt);
      abteilungTable.setItems(list);


      GridPane grid = new GridPane();
      grid.setVgap(20);
      grid.setPadding(new Insets(0, 0, 0, 0));
      grid.setPrefWidth(600);
      grid.add(sachgebietTable,0,0);
      Pane a = new Pane();
      a.setPrefWidth(20);
      grid.addColumn(1,a);
      grid.add(abteilungTable,2,0);


      Scene secondScene = new Scene(grid);
      Stage newWindow = new Stage();
      newWindow.setTitle("Second Stage");
      newWindow.setScene(secondScene);
      newWindow.show();
    }

    /**
     * Logik um eine Organisation zu editieren
     *
     *
     */
    @FXML
    public void editOrganisattion() {
        if(!orgContainer.anyAbteilungExisting()){
          Dialogs.warnDialog("Es existieren keine Organisationen!", "Warnung");
          return;
        }
        int choose = 0;
        if(orgContainer != null) {
            choose = Dialogs.chooseOrgDialog(orgContainer.anySachgebietExisting(),orgContainer.anyAbteilungExisting(),  "bearbeitet");
        } else {
            choose = Dialogs.chooseOrgDialog(false,orgContainer.anyAbteilungExisting(),  "bearbeitet");
        }
        if (choose < 0) {
            System.out.println("[INFO] Vorgang abgebrochen");
        } else if (choose == 0) {
            //Abteilung
            String abt = Dialogs.chooseAbt(orgContainer, "", "Organisation auswählen");
            Abteilung oldAbt = orgContainer.getAbteilungByKuerzel(abt);
            if (abt == null) {
                return;
            } else {
                Pair<Abteilung,String> result = Dialogs.newAbteilungWindow(userContainer,orgContainer.getAbteilungByKuerzel(abt));
                if(result == null) return;
                if(result.getValue() != null && result.getKey() == null){
                    Dialogs.warnDialog(result.getValue(),"Warnung");
                    return;
                } else {
                    orgContainer.editAbteilung(oldAbt, result.getKey());
                }
            }
        } else if (choose == 1) {
            //Sachgebiet
            if(orgContainer.anySachgebietExisting()) {
                String sach = Dialogs.chooseSach(orgContainer, "", "Organisation auswählen");
                Sachgebiet oldSach = orgContainer.getSachgebietByKuerzel(sach);
                if (sach == null) {
                    return;
                } else {
                    Pair<Sachgebiet, String> result = Dialogs.newSachgebietWindow(orgContainer, userContainer, orgContainer.getSachgebietByKuerzel(sach));
                    if (result == null) return;
                    if (result.getValue() != null && result.getKey() == null) {
                        Dialogs.warnDialog(result.getValue(), "Warnung");
                        return;
                    } else {
                        orgContainer.editSachgebiet(oldSach, result.getKey());
                        orgContainer.renameInventare(path,oldSach.getKuerzel(),result.getKey().getKuerzel());
                        fillInventoryBox();
                    }
                }
            } else {
                System.out.println("Kein Sachgebiet gefunden");
            }
        }
        orgContainer.safeOrganisationsData();
    }

    /**
     * Löscht eine vorhandene Organisation
     *
     *
     */
    @FXML
    public void deleteOrgClicked(){
        int choose = 0;

        if(!orgContainer.anyAbteilungExisting()){
            Dialogs.warnDialog("Es existieren keine Organisationen!", "Warnung");
            return;
        }

        if(orgContainer != null) {
            choose = Dialogs.chooseOrgDialog(orgContainer.anySachgebietExisting(),orgContainer.anyAbteilungExisting(), "gelöscht");
        } else {
            choose = Dialogs.chooseOrgDialog(false, orgContainer.anyAbteilungExisting(), "gelöscht");
        }

        if (choose < 0) {
            System.out.println("[INFO] Vorgang abgebrochen");
        } else if (choose == 0) {
            //Abteilung
            if(orgContainer.anyAbteilungExisting()) {
                String abt = Dialogs.chooseAbt(orgContainer, "Organisation auswählen", "Abteilung Wählen");
                if(abt == null){
                  return;
                }
                if(Dialogs.confirmDialog("Abteilung " + abt + " wirklich löschen?")) {
                    orgContainer.deleteOrg(orgContainer.getAbteilungByKuerzel(abt));
                }
            }
        } else if (choose == 1) {
            //Sachgebiet
            if(orgContainer.anySachgebietExisting()) {
                String sach = Dialogs.chooseSach(orgContainer, "Organisation auswählen", "Sachgebiet Wählen");
                if(sach == null){
                  return;
                }
                if(Dialogs.confirmDialog("Sachgebiet " + sach + " wirklich löschen?")) {
                    orgContainer.deleteOrg(orgContainer.getSachgebietByKuerzel(sach));
                }
            }
        }
        orgContainer.safeOrganisationsData();
        initialize();
    }
    /**
     * setzt das Look and Feel für Swing elemente
     * müsste noch durch Javafx ersetzt werden
     */
    private void setLookAndFeel(){
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException|InstantiationException|UnsupportedLookAndFeelException|IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
