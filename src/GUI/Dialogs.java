package GUI;

import Data.Abteilung;
import Data.Person;
import Data.Sachgebiet;
import Verwaltung.OrganisationContainer;
import Verwaltung.UserContainer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Sammelklasse für alle möglichen Dialoge die nicht zur eingabe von Assetdaten dienen
 */
public class Dialogs {
    public static void warnDialog(String warning, String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warnung");
        alert.setHeaderText(header);
        alert.setContentText(warning);

        alert.showAndWait();
    }

    /**
     * Ruft ein bestätigungsdialog auf
     * @param confirmation Ist die Nachricht an den Nutzer die er bestätigen soll
     * @return boolean ob er bestätigt hat oder nicht
     */
    public static boolean confirmDialog(String confirmation) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(confirmation);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Dialog für die Eingabe eines Inventarnamens
     * @param orgs container für Organisationsstruktur
     * @param title Titel des Dialogs
     * @param header header des Dialogs
     * @return Inventarname
     */
    public static String inventoryNameDialog(OrganisationContainer orgs, String title, String header){
        ObservableList<String> observableList =
                FXCollections.observableArrayList(
                        orgs.getAllSachgebietsKuerzel());

        for(String a : orgs.getAllSachgebietsKuerzel()){
            System.out.println(a);
        }

        TextField name = new TextField();

        ComboBox org = new ComboBox(observableList);
        org.setValue(orgs.getAllSachgebietsKuerzel()[0]);

        Dialog<String> dialog  = new Dialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        ButtonType OK_Button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OK_Button, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Inventarname: "), 0, 0);
        grid.add(org, 1, 0);
        grid.add(name, 2, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == OK_Button && !(name.getText().equals(""))){
                return org.getValue() + " " + name.getText();
            } else {
                return null;
            }
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("[INFO] Inventarname: " + result.get());
            return result.get();
        } else {
            warnDialog("Bitte einen Namen vergeben!", "Info");
            return null;
        }
    }

    /**
     * Dialog um das Passwort zu ändern
     * @param user Nutzer dessen Passwort geändert werden soll
     * @return Pair aus Neus Passwort und Fehlermeldung
     */
    public static Pair changePw(Person user){

        PasswordField oldPW = new PasswordField();
        PasswordField newPW = new PasswordField();
        PasswordField newConfirm = new PasswordField();

        Dialog<Pair<String,String>> dialog = new Dialog<>();
        dialog.setTitle("Passwort ändern");

        ButtonType OK_Button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OK_Button, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Altes Passwort: "), 0, 0);
        grid.add(oldPW, 1, 0);
        grid.add(new Label("Neues Passwort: "), 0, 1);
        grid.add(newPW, 1, 1);
        grid.add(new Label("Passwort bestätigen: "), 0, 2);
        grid.add(newConfirm, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {

            boolean OKButton, confirmed, login;
            OKButton = dialogButton == OK_Button;
            confirmed = newPW.getText().equals(newConfirm.getText());
            login = oldPW.getText().equals(user.getPassword());

            if(OKButton && confirmed && login){
                return new Pair<>(newPW.getText(), null);
            } else if(dialogButton == ButtonType.CANCEL){
                return new Pair<>("cancelled","[INFO] Vorgang abgebrochen!");
            } else if(!(oldPW.getText().equals(user.getPassword()))){
                return new Pair<>(null,"Passwort nicht korrekt!");
            } else if(oldPW.getText().equals("") || newPW.getText().equals("") || newConfirm.getText().equals("")){
                return new Pair<>(null,"Alle Felder müssen Ausgefüllt werden!");
            } else if(dialogButton == OK_Button && !(newPW.getText().equals(newConfirm.getText()))){
                return new Pair<>(null,"Passwörter stimmen nicht überein!");
            }
            return new Pair<>(null,null);
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();

        if(result.isPresent()) {
            return result.get();
        } else {
            return new Pair<>(null,"[WARNING] Etwas ist Schiefgelaufen");
        }
    }

    /**
     * Fenster um einen Nutzer zu bearbeiten
     * @param person Nutzer der bearbeitet wird
     * @param logedPerson eingeloggter Nutzer
     * @return Der fertig bearbeitete Nutzer
     */
    public static Person editUserWindow(Person person, Person logedPerson){
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle("Benutzer Editieren");
        ButtonType addButton = new ButtonType("Bestätigen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField firstName = new TextField();
        firstName.setText(person.getSurname());
        TextField secoundName = new TextField();
        secoundName.setText(person.getName());

        ObservableList<String> genderOptions =
                FXCollections.observableArrayList(
                        "Männlich",
                        "Weiblich"
                );
        ComboBox gender = new ComboBox(genderOptions);
        if(person.isMan()) {
            gender.setValue("Männlich");
        } else {
            gender.setValue("Weiblich");
        }

        ObservableList<String> adminOptions =
                FXCollections.observableArrayList(
                        "Ja",
                        "Nein"
                );
        ComboBox admin = new ComboBox(adminOptions);
        if(person.isAdmin()) {
            admin.setValue("Ja");
        } else {
            admin.setValue("Nein");
        }

        ComboBox blockedBox = new ComboBox();

        if(logedPerson.isAdmin()){
            ObservableList<String> userblocked =
                    FXCollections.observableArrayList(
                            "Ja",
                            "Nein"
                    );
            blockedBox = new ComboBox(userblocked);
            if(person.isLocked()){
                blockedBox.setValue("Ja");
            } else {
                blockedBox.setValue("Nein");
            }
        }

        grid.add(new Label("Vorname: "), 0, 0);
        grid.add(firstName,1,0);
        grid.add(new Label("Nachname: "), 0, 1);
        grid.add(secoundName,1,1);
        grid.add(new Label("Geschlecht: "), 0, 2);
        grid.add(gender,1,2);

        grid.add(new Label("Admin: "), 0, 5);
        grid.add(admin,1,5);

        if(logedPerson.isAdmin()){
            grid.add(new Label("Blockiert: "), 0,6);
            grid.add(blockedBox,1,6);
        }

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> firstName.requestFocus());

        ComboBox finalBlockedBox = blockedBox;
        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == addButton){
                Person edited = person;

                edited.setName(secoundName.getText());
                edited.setSurname(firstName.getText());
                if(gender.getValue().equals("Männlich")){
                    edited.setMan(true);
                } else {
                    edited.setMan(false);
                }
                if(admin.getValue().equals("Ja")){
                    edited.setAdmin(true);
                } else {
                    edited.setAdmin(false);
                }
                if(logedPerson.isAdmin() && finalBlockedBox.getValue().equals("Ja")) {
                    edited.setLocked(true);
                } else {
                    edited.setLocked(false);
                }
                return edited;
            } else if(dialogButton == ButtonType.CANCEL){
                return null;
            }
            return null;
        });

        Optional<Person> result = dialog.showAndWait();
        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    /**
     * Dialog in dem man seine Filter festlegen kann
     * @return Array aus bool werten
     *             filter[0] = BodenUndGebaeude
     *             filter[1] = Fuhrpark
     *             filter[2] = Hardware
     *             filter[3] = Mobiliar
     *             filter[4] = Software
     *             filter[5] = Sonstiges
     */
    public static boolean[] getFilter(){
        Dialog<Boolean[]> dialog = new Dialog<>();
        dialog.setTitle("Wonach soll gefiltert werden?");
        ButtonType addButton = new ButtonType("Bestätigen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        CheckBox BodenGebaeude = new CheckBox("Boden und Gebäude");
        CheckBox Fuhrpark = new CheckBox("Fuhrpark");
        CheckBox Hardware = new CheckBox("Hardware");
        CheckBox Mobiliar = new CheckBox("Mobiliar");
        CheckBox Software = new CheckBox("Software");
        CheckBox Sonstiges = new CheckBox("Sonstiges");

        grid.add(BodenGebaeude,0,0);
        grid.add(Fuhrpark,0,1);
        grid.add(Hardware,0,2);
        grid.add(Mobiliar,0,3);
        grid.add(Software,0,4);
        grid.add(Sonstiges,0,5);


        dialog.getDialogPane().setContent(grid);


        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == addButton) {
                boolean anyTrue = false;
                Boolean[] filter = new Boolean[6];
                for(int i = 0; i < filter.length; i++){
                    filter[i]=false;
                }

                if (BodenGebaeude.isSelected()) filter[0] = true;
                if (Fuhrpark.isSelected())      filter[1] = true;
                if (Hardware.isSelected())      filter[2] = true;
                if (Mobiliar.isSelected())      filter[3] = true;
                if (Software.isSelected())      filter[4] = true;
                if (Sonstiges.isSelected())     filter[5] = true;

                for (int i = 0; i < filter.length; i++) {
                    if (filter[i]) {
                        anyTrue = true;
                        break;
                    }
                }
                if (!anyTrue) {
                    return null;
                }
                return filter;
            } else {
                return null;
            }
        });

        Optional<Boolean[]> result = dialog.showAndWait();

        if(result.isPresent()){
            boolean[] returnArray = new boolean[6];
            Boolean[] resultArray = result.get();
            for(int i = 0; i < resultArray.length; i++){
                returnArray[i] = resultArray[i];
            }
            return returnArray;
        } else {
            return null;
        }
    }

    /**
     * Dialolg in dem die art der Organisation ausgewählt wird
     * @param sachExisting boolean ob überhaupt ein sachgebiet existiert
     * @param abtExisting boolean ob überhaupt eine Abteilung existiert
     * @param operation String welche operation mit der ausgewählten organisation durchgeführt wird
     * @return
     */
    public static int chooseOrgDialog(boolean sachExisting, boolean abtExisting, String operation){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Organisation anlegen");
        alert.setHeaderText("Welche Organisationsform soll " + operation + " werden?");
        alert.setContentText("Optionen: ");

        ButtonType buttonTypeOne = new ButtonType("Abteilung");
        ButtonType buttonTypeTwo = new ButtonType("Sachgebiet");
        ButtonType buttonTypeCancel = new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE);

        if(abtExisting && operation.equals("angelegt")){
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        } else if((operation.equals("gelöscht") || operation.equals("bearbeitet")) && sachExisting){
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
        } else {
            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            return 0;
        } else if (result.get() == buttonTypeTwo) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Dialog für das erstellen oder bearbeiten einer Abteilung
     *
     * @param userContainer container für die Benutzerdaten
     * @param actual Abteilung die bearbeitet werden soll
     * @return paar aus der angelegten oder bearbeiteten Abteilung und falls aufgetreten eine fehlermeldung
     */
    public static Pair<Abteilung,String> newAbteilungWindow(UserContainer userContainer, Abteilung actual){
        ObservableList<String> userList =
            FXCollections.observableArrayList(
            userContainer.getUserNamesWithoutAdmin()
        );

        ComboBox<String> userBox = new ComboBox<>(userList);
        TextField nameField = new TextField();
        TextField shortcutField = new TextField();

        userBox.setValue(userList.get(0));

        Dialog<Pair<Abteilung,String>> dialog = new Dialog<>();
        dialog.setTitle("Neue Abteilung anlegen!");
        ButtonType addButton = new ButtonType("Bestätigen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        if(actual != null){
            nameField.setText(actual.getName());
            shortcutField.setText(actual.getKuerzel());
        }

        grid.add(userBox, 1,0);
        grid.add(new Label("Leiter"), 0, 0);

        grid.add(new Label("Name"),0,1);
        grid.add(nameField, 1, 1);

        grid.add(new Label("Kürzel"), 0,2);
        grid.add(shortcutField,1,2);

        dialog.getDialogPane().setContent(grid);


        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == addButton) {
                Abteilung newAbt = new Abteilung();
                if(actual != null){
                    newAbt = actual;
                }
                if(nameField.getText().equals("") || shortcutField.getText().equals("")){
                    return new Pair<>(null,"Alle Felder müssen ausgefüllt werden");
                }

                newAbt.setName(nameField.getText());
                newAbt.setKuerzel(shortcutField.getText());
                newAbt.setLeiter(userContainer.getPersonByUsername(userBox.getValue()));

                return new Pair<>(newAbt,null);
            } else {
                return null;
            }
        });

        Optional<Pair<Abteilung,String>> result = dialog.showAndWait();

        if(result.isPresent()){
          return result.get();
        } else {
          return null;
        }
    }
    /**
     * Dialog für das erstellen oder bearbeiten eines Sachgebiets
     *
     * @param orgContainer Container für die Organisations struktur
     * @param userContainer container für die Benutzerdaten
     * @param actual Sachgebiet das bearbeitet werden soll
     * @return paar aus der angelegten oder bearbeiteten Abteilung und falls aufgetreten eine fehlermeldung
     */
  public static Pair<Sachgebiet,String> newSachgebietWindow(OrganisationContainer orgContainer, UserContainer userContainer, Sachgebiet actual){
    ObservableList<String> orgList =
            FXCollections.observableArrayList(
                    orgContainer.getAllAbteilungsKuerzel()
            );

    ObservableList<String> userList =
            FXCollections.observableArrayList(
                    userContainer.getUserNamesWithoutAdmin()
            );

    ComboBox<String> abtBox = new ComboBox<>(orgList);
    abtBox.setValue(orgContainer.getAllAbteilungsKuerzel()[0]);
    ComboBox<String> userBox = new ComboBox<>(userList);
    userBox.setValue(userContainer.getUserNamesWithoutAdmin()[0]);
    TextField nameField = new TextField();
    TextField shortcutField = new TextField();

    if(actual != null){
        abtBox.setValue(actual.getAbtKuerzel());
        userBox.setValue(actual.getLeiter().getUsername());
        nameField.setText(actual.getName());
        shortcutField.setText(actual.getKuerzel());
    }

    userBox.setValue(userList.get(0));

    Dialog<Pair<Sachgebiet,String>> dialog = new Dialog<>();
    dialog.setTitle("Neues Sachgebiet anlegen!");
    ButtonType addButton = new ButtonType("Bestätigen" ,ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20,150,10,10));

    grid.add(userBox, 1,0);
    grid.add(new Label("Leiter"), 0, 0);

    grid.add(abtBox, 1,1);
    grid.add(new Label("Abteilung"), 0, 1);

    grid.add(new Label("Name"),0,2);
    grid.add(nameField, 1, 2);

    grid.add(new Label("Kürzel"), 0,3);
    grid.add(shortcutField,1,3);

    dialog.getDialogPane().setContent(grid);


    dialog.setResultConverter(dialogButton -> {
      if(dialogButton == addButton) {
        Sachgebiet newSach = new Sachgebiet();
        if(nameField.getText().equals("") || shortcutField.getText().equals("")){
          return new Pair<>(null,"Alle Felder müssen ausgefüllt werden");
        }
        newSach.setAbtKuerzel(abtBox.getValue());
        newSach.setName(nameField.getText());
        newSach.setKuerzel(shortcutField.getText());
        newSach.setLeiter(userContainer.getPersonByUsername(userBox.getValue()));

        return new Pair<>(newSach,abtBox.getValue());
      } else {
        return null;
      }
    });

    Optional<Pair<Sachgebiet,String>> result = dialog.showAndWait();

    if(result.isPresent()){
      return result.get();
    } else {
      return null;
    }
  }

    /**
     * Dialog um eine Abteilung auszuwählen
     * @param orgs Container für Organisationsstruktur
     * @param title titel
     * @param header header
     * @return Kuerzel der Abteilung
     */
    public static String chooseAbt(OrganisationContainer orgs, String title, String header){
        ObservableList<String> observableList =
                FXCollections.observableArrayList(
                        orgs.getAllAbteilungsKuerzel()
                        );

        ComboBox org = new ComboBox(observableList);
        Abteilung _default = orgs.getAbteilungArrayList().get(0);
        org.setValue(_default.getKuerzel());

        Dialog<String> dialog  = new Dialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        ButtonType OK_Button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OK_Button, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Abteilung: "), 0, 0);
        grid.add(org, 1, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == OK_Button){
                return (String) org.getValue();
            } else {
                return null;
            }
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        } else {
            return null;
        }
    }
    /**
     * Dialog um ein Sachgebiet auszuwählen
     * @param orgs Container für Organisationsstruktur
     * @param title titel
     * @param header header
     * @return Kuerzel des Sachgebiets
     */
    public static String chooseSach(OrganisationContainer orgs, String title, String header){
        ObservableList<String> observableList =
                FXCollections.observableArrayList(
                        orgs.getAllSachgebietsKuerzel());

        ComboBox org = new ComboBox(observableList);
        org.setValue(orgs.getAllSachgebietsKuerzel()[0]);

        Dialog<String> dialog  = new Dialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        ButtonType OK_Button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OK_Button, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Sachgebiet: "), 0, 0);
        grid.add(org, 1, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == OK_Button){
                return (String) org.getValue();
            } else {
                return null;
            }
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            return result.get();
        } else {
            return null;
        }
    }
}
