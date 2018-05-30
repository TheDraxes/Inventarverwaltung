package GUI.ViewGUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewController {

    @FXML
    private ScrollPane ItemScrollPane;

    @FXML
    void ShowClicked(ActionEvent event) {
        VBox test = new VBox();
        test.getChildren().addAll(new Text("Test"));
        ItemScrollPane.setContent(test);
    }
}
