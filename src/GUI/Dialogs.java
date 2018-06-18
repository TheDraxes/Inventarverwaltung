package GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

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
            System.out.println("**Inventarname: " + result.get());
        }
        return result.get();
    }

    public static String inputDialog(String title, String header){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("**Inventarname: " + result.get());
        }
        return result.get();
    }
}
