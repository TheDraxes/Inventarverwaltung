package GUI.ViewGUI.NewItemDialogs;

import Data.Asset;
import Data.Software;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Date;
import java.util.Optional;

/**
 * Klasse die das Softwaredialog aufbaut
 *
 * @auther Tim
 * @version 1.0
 */

public class SoftwareDialog extends AbstractDialog{
  public SoftwareDialog(String[] labelNames, Label[] labels, TextField[] textFields, Asset actual) {
    super(labelNames, labels, textFields, actual);
  }

  public Pair<Asset, String> getSoftware() {


    Software edit = (Software) actual;

    if (edit != null) {
      this.TextFields[0].setText(edit.getBezeichnung());
      this.TextFields[1].setText("" + edit.getAnschaffungswert());
      this.TextFields[2].setText("" + edit.getTnd());
      this.TextFields[3].setText("" + edit.getAnzahl());

      if (edit.getVersion() == null || edit.getVersion().equals("")) {
        TextFields[4].setPromptText("Keine Angaben");
      } else {
        TextFields[4].setText(edit.getVersion());
      }
    }
      Dialog<Pair<Asset, String>> dialog = new Dialog<>();

      String buttonText = "";

      if (actual != null) {
        buttonText = "Fertig";
      } else {
        buttonText = "Hinzufügen";
      }

      dialog.setTitle("Neues Item");
      ButtonType addButton = new ButtonType(buttonText, ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
      dialog.getDialogPane().setStyle("-fx-background-color:  #b5edff");

      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));

      for (int i = 0; i < TextFields.length; i++) {
        grid.add(Labels[i], 0, i);
        grid.add(TextFields[i], 1, i);
      }

      dialog.getDialogPane().setContent(grid);

      dialog.setResultConverter(dialogButton -> {
        if (dialogButton == addButton) {
          Software software = new Software();

          if (actual != null) {
            software.setInventarnummer(actual.getInventarnummer());
          }

          if (TextFields[0].getText().equals("")) {
            return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
          } else {
            software.setBezeichnung(TextFields[0].getText());
          }

          if (TextFields[1].getText().equals("")) {
            return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
          } else {
            software.setAnschaffungswert(Double.parseDouble(TextFields[1].getText()));
          }

          if (TextFields[2].getText().equals("")) {
            return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
          } else {
            software.setTnd(Integer.parseInt(TextFields[2].getText()));
          }

          if (TextFields[3].getText().equals("")) {
            return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
          } else {
            software.setAnzahl(Integer.parseInt(TextFields[3].getText()));
          }

          if (!(TextFields[4].getText().equals(""))) {
            software.setVersion(TextFields[4].getText());
          }

          software.setInserierungsdatum(new Date());

          return new Pair<>(software, null);
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

