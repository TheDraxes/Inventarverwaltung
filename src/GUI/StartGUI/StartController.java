package GUI.StartGUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class StartController {

    @FXML
    private ComboBox<String> InventarBox;

    @FXML
    private Button newButton;

    @FXML
    private Button ConfirmButton;

    @FXML
    void ComboBoxInit() {
        ObservableList<String> _default = FXCollections.observableArrayList("Option 1", "Option 2");
        InventarBox.setItems(_default);
    }

    @FXML
    void newClicked(ActionEvent event) {

    }

    @FXML
    void confirmClicked(ActionEvent event) {

    }

}
