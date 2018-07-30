package GUI.ViewGUI.NewItemDialogs;

import Data.*;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 * Klasse zur Unterscheidung der einzelnen Asset arten
 *
 * Ruft die einzelnen Dialogklassen auf um die daten eingeben zu lassen
 * und bildet das Fertige Asset
 *
 * @auther Tim
 * @version 1.0
 */

public class AssetDialogs {
  private TextField[] TextFields = new TextField[0];
  private Label[] Labels = new Label[0];
  private String[] labelNames = new String[0];


  public Pair<Asset, String> getNewItem(String itemType, Asset actual) {
    Pair pair;
    switch (itemType) {
      case "Fuhrpark":          //Fuhrpark
        this.labelNames = new Fuhrpark().getParamNames();
        setTextFields();
        pair = new FuhrparkDialog(labelNames, Labels, TextFields, actual).getFuhrpark();
        return pair;
      case "BodenUndGebaeude": //Boden und Gebäude
        this.labelNames = new BodenUndGebaeude().getParamNames();
        setTextFields();
        pair = new BodenUndGebäudeDialog(labelNames,Labels,TextFields,actual).getBodenUndGebäude();
        return pair;
      case "Hardware":          //Hardware
        this.labelNames = new Hardware().getParamNames();
        setTextFields();
        pair = new HardwareDialog(labelNames,Labels,TextFields,actual).getHardware();
        return pair;
      case "Mobiliar":          //Mobiliar
        this.labelNames = new Mobiliar().getParamNames();
        setTextFields();
        pair = new MobiliarDialog(labelNames,Labels,TextFields,actual).getMobiliar();
        return pair;
      case "Software":          //Software
        this.labelNames = new Software().getParamNames();
        setTextFields();
        pair = new SoftwareDialog(labelNames,Labels,TextFields,actual).getSoftware();
        return pair;
      case "Sonstiges":         //Sonstiges
        this.labelNames = new Sonstiges().getParamNames();
        setTextFields();
        pair = new SonstigesDialog(labelNames,Labels,TextFields,actual).getMobiliar();
        return pair;
    }
    return new Pair<>(null, null);
  }

  protected void setTextFields() {
    System.out.println(labelNames.length);
    Labels = new Label[labelNames.length];
    for (int i = 0; i < labelNames.length; i++) {
      if (i < 4) {
        Labels[i] = new Label(labelNames[i] + "*");
      } else {
        Labels[i] = new Label(labelNames[i]);
      }
    }

    TextFields = new TextField[labelNames.length];
    for (int i = 0; i < labelNames.length; i++) {
      TextFields[i] = new TextField();
      TextFields[i].setPromptText(Labels[i].getText());
    }
  }
}
