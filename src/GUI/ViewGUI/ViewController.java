package GUI.ViewGUI;

import data.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewController {

    @FXML
    private ScrollPane ItemScrollPane;

    @FXML
    void ShowClicked(ActionEvent event) {
        VBox test = new VBox();
        test.setSpacing(5);
        Separator VerticalLine = new Separator();
        VerticalLine.setOrientation(Orientation.VERTICAL);
        VerticalLine.setMaxWidth(500);
        for(int i = 0; i <= 20; i++) {
            test.getChildren().addAll(new ItemEntry(new Item("Platzhalter", (double) i)));
        }
        ItemScrollPane.setContent(test);
    }
}
