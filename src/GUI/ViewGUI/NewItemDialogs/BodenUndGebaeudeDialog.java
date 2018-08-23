package GUI.ViewGUI.NewItemDialogs;

import Data.Asset;
import Data.BodenUndGebaeude;
import Data.Fuhrpark;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Date;
import java.util.Optional;

/**
 * Klasse die die eingabemaske für ein Boden und Gebäude Asset aufbaut
 *
 * @author Tim
 */

public class BodenUndGebaeudeDialog extends AbstractDialog{

  public BodenUndGebaeudeDialog(String[] labelNames, Label[] labels, TextField[] textFields, Asset actual) {
    super(labelNames, labels, textFields, actual);
  }

  public Pair<Asset, String> getBodenUndGebaeude() {

    BodenUndGebaeude edit = (BodenUndGebaeude) actual;

    if (edit != null) {
      this.TextFields[0].setText(edit.getBezeichnung());
      this.TextFields[1].setText("" + edit.getAnschaffungswert());
      this.TextFields[2].setText("" + edit.getTnd());

      if (edit.getPlz() == 0) {
        TextFields[3].setPromptText("Keine Angaben");
      } else {
        TextFields[3].setText("" + edit.getPlz());
      }

      if (edit.getOrt() == null || edit.getOrt().equals("") ) {
        TextFields[4].setPromptText("Keine Angaben");
      } else {
        TextFields[4].setText("" + edit.getOrt());
      }

      if (edit.getStrasse() == null || edit.getStrasse().equals("")) {
        TextFields[5].setPromptText("Keine Angaben");
      } else {
        TextFields[5].setText("" + edit.getStrasse());
      }

      if (edit.getHausnummer() == null || edit.getHausnummer().equals("")) {
        TextFields[6].setPromptText("Keine Angaben");
      } else {
        TextFields[6].setText("" + edit.getHausnummer());
      }

      if ((edit.getFlaeche()) == 0D) {
        TextFields[7].setPromptText("Keine Angaben");
      } else {
        TextFields[7].setText("" + edit.getFlaeche());
      }
    }

    Dialog<Pair<Asset, String>> dialog = new Dialog<>();
    String buttonText = "";

    if(actual != null){
      buttonText = "Fertig";
      dialog.setTitle("Editieren: Boden und Gebäude");
    } else {
      buttonText = "Hinzufügen";
      dialog.setTitle("Boden und Gebäude");
    }


    ButtonType addButton = new ButtonType(buttonText, ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
    dialog.getDialogPane().setStyle("-fx-background-color:  #b5edff");

    GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(20, 150, 10, 10));

    for (int i = 0; i < TextFields.length - 1; i++) {
      grid.add(Labels[i], 0, i);
      grid.add(TextFields[i], 1, i);
    }

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == addButton) {
        BodenUndGebaeude bodenUndGebaeude = new BodenUndGebaeude();

        if(actual != null){
          bodenUndGebaeude.setInventarnummer(actual.getInventarnummer());
        }

        if (TextFields[0].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          bodenUndGebaeude.setBezeichnung(TextFields[0].getText());
        }


        String wert = TextFields[1].getText();
        if (wert.equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          if(wert.contains(",")){
            wert = wert.replace(",",".");
          }
          try {
            bodenUndGebaeude.setAnschaffungswert(Double.parseDouble(wert));
          }catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit dem Anschaffungswert! Er darf nur aus Zahlen und einem \",\" oder \".\" bestehen!");
          }
        }

        if (TextFields[2].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          try {
            bodenUndGebaeude.setTnd(Integer.parseInt(TextFields[2].getText()));
          }catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit der Technischen Nutzungsdauer! Es dürfen nur Zahlen verwendet werden!");
          }
        }

        if (!(TextFields[3].getText().equals(""))) {
          try {
            bodenUndGebaeude.setPlz(Integer.parseInt(TextFields[3].getText()));
          }catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit der Postleitzahl! Es dürfen nur Zahlen verwendet werden!");
          }
        }

        if (!(TextFields[4].getText().equals(""))) {
          bodenUndGebaeude.setOrt(TextFields[4].getText());
        }

        if (!(TextFields[5].getText().equals(""))) {
          bodenUndGebaeude.setStrasse(TextFields[5].getText());
        }

        if(!(TextFields[6].getText().equals(""))) {
          bodenUndGebaeude.setHausnummer(TextFields[6].getText());
        }

        wert = TextFields[7].getText();
        if(!(wert.equals(""))) {
          if(wert.contains(",")){
            wert = wert.replace(",",".");
          }
          try {
            bodenUndGebaeude.setFlaeche(Double.parseDouble(wert));
          }catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit der Fläche! Sie darf nur aus Zahlen und einem \",\" oder \".\" bestehen!");
          }
        }

        bodenUndGebaeude.setInserierungsdatum(new Date());
        bodenUndGebaeude.setAnzahl(1);

        return new Pair<>(bodenUndGebaeude, null);
      } else if (dialogButton == ButtonType.CANCEL) {
        return new Pair<>(null, null);
      }
      return new Pair<>(null, "Etwas ist schief gelaufen");
    });

    Optional<Pair<Asset, String>> result = dialog.showAndWait();

    if (result.isPresent()) {
      return result.get();
    } else {
      return new Pair<>(null, "Result war nicht present");
    }
  }
}
