package GUI.ViewGUI.CellFactories;

import Data.Asset;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class AnschaffungswertCellFactory implements Callback<TableColumn<Asset, Double>, TableCell<Asset, Double>> {

  @Override
  public TableCell<Asset, Double> call(TableColumn<Asset, Double> param) {
    TableCell<Asset, Double> cell = new TableCell<Asset, Double>() {

      @Override
      public void updateItem(final Double item, boolean empty) {
        if (item != null) {
          final java.text.DecimalFormatSymbols germany
                  = new java.text.DecimalFormatSymbols( new java.util.Locale( "de", "DE" ));
          final java.text.DecimalFormat german
                  = new java.text.DecimalFormat( "##,###.00", germany );
          setText(german.format(item) + "€");
        }
      }
    };
    return cell;
  }
}

