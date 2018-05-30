package GUI.ViewGUI;

import data.Item;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ItemEntry extends GridPane {
    public ItemEntry(Item newItem){
        this.setPrefSize(588,100);

        Color blue = Color.rgb(184, 211, 252);
        BackgroundFill fill = new BackgroundFill(blue, CornerRadii.EMPTY, Insets.EMPTY);
        this.setBackground(new Background(fill));
        this.setStyle("-fx-border-color: gray");

        this.setHgap(10);
        this.add(new Label("Bezeichnung: " + newItem.getName()), 0, 0);
        this.add(new Label("Value: " + newItem.getValue()), 1, 0);
        this.add(new Label("Value: " + newItem.getValue()), 0, 2);
        this.add(new Label("Bezeichnung: " + newItem.getName()), 1, 2);


        //this.getChildren().addAll(new Label("Bezeichnung: " + newItem.getName()), new Label("Test"));
    }
}
