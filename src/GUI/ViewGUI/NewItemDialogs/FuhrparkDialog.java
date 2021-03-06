package GUI.ViewGUI.NewItemDialogs;

import Data.Asset;
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
 * Klasse die das Fuhrparkdialog aufbaut
 *
 *
 * @version 1.0
 */

public class FuhrparkDialog extends AbstractDialog {
  private ComboBox kw_ps;

  public FuhrparkDialog(String[] labelNames, Label[] labels, TextField[] textFields, Asset actual) {
    super(labelNames, labels, textFields, actual);
  }

  public Pair<Asset, String> getFuhrpark() {

    ObservableList<String> os = FXCollections.observableArrayList("Kw", "Ps");
    this.kw_ps = new ComboBox(os);
    this.kw_ps.setValue("Kw");

    Fuhrpark edit = (Fuhrpark) actual;

    if (edit != null) {
      this.TextFields[0].setText(edit.getBezeichnung());
      this.TextFields[1].setText("" + edit.getAnschaffungswert());
      this.TextFields[2].setText("" + edit.getTnd());

      if (edit.getKennzeichen() == null || edit.getKennzeichen().equals("")) {
        TextFields[3].setPromptText("Keine Angaben");
      } else {
        TextFields[3].setText(edit.getKennzeichen());
      }

      if ((edit.getFahrgestellnummer()) == 0L) {
        TextFields[4].setPromptText("Keine Angaben");
      } else {
        TextFields[4].setText("" + edit.getFahrgestellnummer());
      }

      if (edit.getKilometerstand() == 0.0) {
        TextFields[5].setPromptText("Keine Angaben");
      } else {
        TextFields[5].setText("" + edit.getKilometerstand());
      }

      if (edit.getKw() == 0) {
        TextFields[6].setPromptText("Keine Angaben");
      } else {
        TextFields[6].setText("" + edit.getKw());
      }
    }

    Dialog<Pair<Asset, String>> dialog = new Dialog<>();
    String buttonText = "";

    if(actual != null){
      buttonText = "Fertig";
      dialog.setTitle("Editieren: Fuhrpark");
    } else {
      buttonText = "Hinzufügen";
      dialog.setTitle("Fuhrpark");
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

    grid.add(Labels[Labels.length - 1], 0, Labels.length);
    grid.add(TextFields[TextFields.length - 1], 1, TextFields.length);
    grid.add(kw_ps, 2, TextFields.length);

    dialog.getDialogPane().setContent(grid);

    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == addButton) {
        Fuhrpark fuhrpark = new Fuhrpark();

        if(actual != null){
          fuhrpark.setInventarnummer(actual.getInventarnummer());
        }

        if (TextFields[0].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          fuhrpark.setBezeichnung(TextFields[0].getText());
        }

        String wert = TextFields[1].getText();
        if (wert.equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          if(wert.contains(",")){
            wert = wert.replace(",",".");
          }
          try {
            fuhrpark.setAnschaffungswert(Double.parseDouble(wert));
          } catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit dem Anschaffungswert! Er darf nur aus Zahlen und einem \",\" oder \".\" bestehen!");
          }
        }

        if (TextFields[2].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          try {
            fuhrpark.setTnd(Integer.parseInt(TextFields[2].getText()));
          } catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit der Technischen Nutzungsdauer! Es dürfen nur Zahlen verwendet werden!");
          }
        }

        if (!(TextFields[3].getText().equals(""))) {
          fuhrpark.setKennzeichen(TextFields[3].getText());
        }

        if (!(TextFields[4].getText().equals(""))) {
          fuhrpark.setFahrgestellnummer(Long.parseLong(TextFields[4].getText()));
        }

        if (!(TextFields[5].getText().equals(""))) {
          try {
            fuhrpark.setKilometerstand(Integer.parseInt(TextFields[5].getText()));
          }catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit der Anzahl! Es dürfen nur Zahlen verwendet werden!");
          }
        }

        if(!(TextFields[6].getText().equals(""))) {
          try {
            if ((kw_ps.getValue()).equals("Kw")) {
              fuhrpark.setKw(Integer.parseInt(TextFields[6].getText()));
            } else {
              fuhrpark.setPs(Integer.parseInt(TextFields[6].getText()));
            }
          } catch (Exception e){
            return new Pair<>(null, "Etwas Stimmt nicht mit der Leistung! Es dürfen nur Zahlen verwendet werden!");
          }
        }
        fuhrpark.setInserierungsdatum(new Date());
        fuhrpark.setAnzahl(1);

        return new Pair<>(fuhrpark, null);
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
