package GUI;

import Data.Person;
import Verwaltung.UserContainer;
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
        }
        return result.get();
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

        TextField oldPW = new TextField();
        TextField newPW = new TextField();
        TextField newConfirm = new TextField();

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
}
