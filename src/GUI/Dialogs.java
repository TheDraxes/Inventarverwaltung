package GUI;

import Data.Person;
import Verwaltung.UserContainer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import javax.xml.soap.Text;
import java.util.Optional;

public class Dialogs {
    public static void warnDialog(String warning, String header){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Warnung");
        alert.setHeaderText(header);
        alert.setContentText(warning);

        alert.showAndWait();
    }

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

    public static String inputDialog(String defaultValue, String title, String header){
        TextInputDialog dialog = new TextInputDialog(defaultValue);
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("[INFO] Inventarname: " + result.get());
            return result.get();
        } else {
            return null;
        }
    }

    public static String inputDialog(String title, String header){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("[INFO] Inventarname: " + result.get());
        }
        return result.get();
    }

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
            } else if(!(oldPW.equals(user.getPassword()))){
                return new Pair<>(null,"[WARNING] Passwort nicht Korrekt!");
            }else if(oldPW.getText().equals("") || newPW.getText().equals("") || newConfirm.getText().equals("")){
                return new Pair<>(null,"[WARNING] Alle Felder müssen Ausgefüllt werden!");
            } else if(dialogButton == OK_Button && !(newPW.getText().equals(newConfirm.getText()))){
                return new Pair<>(null,"[WARNING] Passwörter stimmmen nicht überein!");
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

    public static Person editUserWindow(Person person){
        Dialog<Person> dialog = new Dialog<>();
        dialog.setTitle("Benutzer Editieren");
        ButtonType addButton = new ButtonType("Bestätigen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        TextField firstName = new TextField();
        firstName.setText(person.getName());
        TextField secoundName = new TextField();
        secoundName.setText(person.getSurname());

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

        grid.add(new Label("Vorname: "), 0, 0);
        grid.add(firstName,1,0);
        grid.add(new Label("Nachname: "), 0, 1);
        grid.add(secoundName,1,1);
        grid.add(new Label("Geschlecht: "), 0, 2);
        grid.add(gender,1,2);

        grid.add(new Label("Admin: "), 0, 5);
        grid.add(admin,1,5);

        dialog.getDialogPane().setContent(grid);

        Platform.runLater(() -> firstName.requestFocus());

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == addButton){
                Person edited = person;
                String[] array = new String[6];
                edited.setName(firstName.getText());
                edited.setSurname(secoundName.getText());
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

    public static boolean[] getFilter(){
        Dialog<Boolean[]> dialog = new Dialog<>();
        dialog.setTitle("Wonach soll gefiltert werden?");
        ButtonType addButton = new ButtonType("Bestätigen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        CheckBox BodenGebäude = new CheckBox("Boden und Gebäude");
        CheckBox Fuhrpark = new CheckBox("Fuhrpark");
        CheckBox Hardware = new CheckBox("Hardware");
        CheckBox Mobiliar = new CheckBox("Mobiliar");
        CheckBox Software = new CheckBox("Software");
        CheckBox Sonstiges = new CheckBox("Sonstiges");

        grid.add(BodenGebäude,0,0);
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

                if (BodenGebäude.isSelected()) {
                    filter[0] = true;
                } else {
                    filter[0] = false;
                }

                if (Fuhrpark.isSelected()) {
                    filter[1] = true;
                } else {
                    filter[1] = false;
                }

                if (Hardware.isSelected()) {
                    filter[2] = true;
                } else {
                    filter[2] = false;
                }

                if (Mobiliar.isSelected()) {
                    filter[3] = true;
                } else {
                    filter[3] = false;
                }

                if (Software.isSelected()) {
                    filter[4] = true;
                } else {
                    filter[4] = false;
                }

                if (Sonstiges.isSelected()) {
                    filter[5] = true;
                } else {
                    filter[5] = false;
                }

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
}
