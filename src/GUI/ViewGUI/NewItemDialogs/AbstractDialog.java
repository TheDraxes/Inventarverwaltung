package GUI.ViewGUI.NewItemDialogs;

import Data.Asset;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Abstrakte Klasse für alle Asseteingabe Dialoge
 */

public abstract class AbstractDialog {
  protected TextField[] TextFields;
  protected Label[] Labels;
  protected String[] labelNames;

  protected Asset actual;

  //Konstruktor
  public AbstractDialog(String[] labelNames, Label[] labels, TextField[] textFields, Asset actual) {
    this.labelNames = labelNames;
    this.Labels = labels;
    this.TextFields = textFields;
    this.actual = actual;
  }
}
