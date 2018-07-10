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
      this.TextFields[3].setText("" + edit.getAnzahl());

      if (edit.getKennzeichen() == null || edit.getKennzeichen().equals("")) {
        TextFields[4].setPromptText("Keine Angaben");
      } else {
        TextFields[4].setText(edit.getKennzeichen());
      }

      if ((edit.getFahrgestellnummer()) == 0L) {
        System.out.println(edit.getFahrgestellnummer());
        TextFields[5].setPromptText("Keine Angaben");
      } else {
        TextFields[5].setText("" + edit.getFahrgestellnummer());
      }

      if (edit.getKilometerstand() == 0.0) {
        TextFields[6].setPromptText("Keine Angaben");
      } else {
        TextFields[6].setText("" + edit.getKilometerstand());
      }

      if (edit.getKw() == 0) {
        TextFields[7].setPromptText("Keine Angaben");
      } else {
        TextFields[7].setText("" + edit.getKw());
      }
    }

    Dialog<Pair<Asset, String>> dialog = new Dialog<>();

    String buttonText = "";

    if(actual != null){
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

        if (TextFields[1].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          fuhrpark.setAnschaffungswert(Double.parseDouble(TextFields[1].getText()));
        }

        if (TextFields[2].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          fuhrpark.setTnd(Integer.parseInt(TextFields[2].getText()));
        }

        if (TextFields[3].getText().equals("")) {
          return new Pair<>(null, "Alle Felder mit einem * müssen ausgefüllt sein!");
        } else {
          fuhrpark.setAnzahl(Integer.parseInt(TextFields[3].getText()));
        }

        if (!(TextFields[4].getText().equals(""))) {
          fuhrpark.setKennzeichen(TextFields[4].getText());
        }

        if (!(TextFields[5].getText().equals(""))) {
          fuhrpark.setFahrgestellnummer(Long.parseLong(TextFields[5].getText()));
        }

        if (!(TextFields[6].getText().equals(""))) {
          fuhrpark.setKilometerstand(Integer.parseInt(TextFields[6].getText()));
        }

        if(!(TextFields[7].getText().equals(""))) {
          if ((kw_ps.getValue()).equals("Kw")) {
            fuhrpark.setKw(Integer.parseInt(TextFields[7].getText()));
          } else {
            fuhrpark.setPs(Integer.parseInt(TextFields[7].getText()));
          }
        }
        fuhrpark.setInserierungsdatum(new Date());

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
