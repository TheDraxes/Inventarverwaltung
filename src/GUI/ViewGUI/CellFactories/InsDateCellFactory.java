package GUI.ViewGUI.CellFactories;

import Data.Asset;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InsDateCellFactory implements Callback<TableColumn<Asset, Date>, TableCell<Asset, Date>> {
  @Override
  public TableCell<Asset, Date> call(TableColumn<Asset, Date> param) {
    TableCell<Asset, Date> cell = new TableCell<Asset, Date>() {
      @Override
      public void updateItem(final Date item, boolean empty) {
        if (item != null) {
          SimpleDateFormat df = new SimpleDateFormat( "dd-MM-yyyy" );
          setText(df.format(item));
        }
      }
    };
    return cell;
  }
}
