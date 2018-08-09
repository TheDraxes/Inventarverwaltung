package GUI.ViewGUI.Summary;

import GUI.Dialogs;
import Verwaltung.AssetContainer;
import javafx.fxml.FXML;

import java.util.ArrayList;

public class SummaryController {
  private boolean ActiveFilter = false;
  private ArrayList filteredList = new ArrayList();
  private AssetContainer assetContainer = new AssetContainer();



  private void fillTable(){

  }



  @FXML
  protected void filterClicked (){
    boolean[] filter = Dialogs.getFilter();
    if(filter != null){
      ActiveFilter = true;
      filteredList = assetContainer.getAssetsByFilter(filter);
    } else {
      ActiveFilter = false;
      filteredList = null;
    }
    fillTable();
  }
}
