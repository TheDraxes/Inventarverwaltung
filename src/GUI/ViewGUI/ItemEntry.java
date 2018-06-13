package GUI.ViewGUI;

import Item.Item;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/*
    Diese Klasse baut ein GridPane mit einigen Werten der Items auf.

    Heraus kommt ein Viereck mit Blauen Hintergrund und einem Grauen Rahmen.

    Farbstil kann noch angepasst werden.
 */

public class ItemEntry extends GridPane {
    public ItemEntry(Item newItem){
        //Größe des Inventareintrags festlegen
        this.setPrefSize(588,100);

        //Hintergrund und Rahmenfarbe festlegen und als Hintergrund setzen
        Color blue = Color.rgb(184, 211, 252);
        BackgroundFill fill = new BackgroundFill(blue, CornerRadii.EMPTY, Insets.EMPTY);
        this.setBackground(new Background(fill));
        this.setStyle("-fx-border-color: gray");

        //Abstand zwischen den Einträgen
        this.setHgap(10);

        //String werte an das GridPane anhängen
        /*
        this.add(new Label("Bezeichnung: " + newItem.getName()), 0, 0);
        this.add(new Label("Value: " + newItem.getValue()), 1, 0);
        this.add(new Label("Value: " + newItem.getValue()), 0, 2);
        this.add(new Label("Bezeichnung: " + newItem.getName()), 1, 2);
        */
    }
}
