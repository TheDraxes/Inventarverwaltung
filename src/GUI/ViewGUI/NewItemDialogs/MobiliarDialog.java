package GUI.ViewGUI.NewItemDialogs;

import Data.Asset;
import Data.Hardware;
import Data.Mobiliar;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Date;
import java.util.Optional;
/**
 * Klasse die die eingabemaske für ein Mobiliar Asset aufbaut
 *
 *
 */
public class MobiliarDialog extends AbstractDialog{

  public MobiliarDialog(String[] labelNames, Label[] labels, TextField[] textFields, Asset actual) {
    super(labelNames, labels, textFields, actual);
  }

  public Pair<Asset, String> getMobiliar() {


    Mobiliar edit = (Mobiliar) actual;

    if (edit != null) {
      this.TextFields[0].setText(edit.getBezeichnung());
      this.TextFields[1].setText("" + edit.getAnschaffungswert());
      this.TextFields[2].setText("" + edit.getTnd());
      this.TextFields[3].setText("" + edit.getAnzahl());

      if (edit.getRaum() == 0) {
        TextFields[4].setPromptText("Keine Angaben");
      } else {
        TextFields[4].setText(""+edit.getRaum());
      }
    }
    Dialog<Pair<Asset, String>> dialog = new Dialog<>();

    String buttonText = "";

    if (actual != null) {
      buttonText = "Fertig";
      dialog.setTitle("Editieren: Mobiliar");
    } else {
      buttonText = "Hinzufügen";
      dialog.setTitle("Mobiliar");
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
        Mobiliar mobi = new Mobiliar();

        if (actual != null) {
          mobi.setInventarnummer(actual.getInventarnummer());
        }

        if (TextFields[0].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          mobi.setBezeichnung(TextFields[0].getText());
        }

        String wert = TextFields[1].getText();
        if (wert.equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          if(wert.contains(",")){
            wert = wert.replace(",",".");
          }
          try {
            mobi.setAnschaffungswert(Double.parseDouble(wert));
          } catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit dem Anschaffungswert! Er darf nur aus Zahlen und einem \",\" oder \".\" bestehen!");
          }
        }

        if (TextFields[2].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          mobi.setTnd(Integer.parseInt(TextFields[2].getText()));
        }

        if (TextFields[3].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          mobi.setAnzahl(Integer.parseInt(TextFields[3].getText()));
        }

        if (!(TextFields[4].getText().equals(""))) {
          mobi.setRaum(Integer.parseInt(TextFields[4].getText()));
        }

        mobi.setInserierungsdatum(new Date());

        return new Pair<>(mobi, null);
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
