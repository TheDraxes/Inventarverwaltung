package TestKlassen;

import Data.Abteilung;
import Data.Sachgebiet;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

/**
 *
 *
 */

public class DialogsTest extends Application{



  public static void main (String[] args){
    launch();

  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    TableView<Sachgebiet> sachgebietTable = new TableView();

    TableColumn nameSach = new TableColumn("Sachgebiet");
    nameSach.setPrefWidth(125);
    nameSach.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn kuerzelSach = new TableColumn("Kürzel");
    kuerzelSach.setPrefWidth(125);

    sachgebietTable.getColumns().addAll(nameSach,kuerzelSach);


    TableView<Abteilung> abteilungTable = new TableView();

    TableColumn nameAbt = new TableColumn("Abteilung");
    nameAbt.setPrefWidth(125);

    TableColumn kuerzelAbt = new TableColumn("Kürzel");
    kuerzelAbt.setPrefWidth(125);

    abteilungTable.getColumns().addAll(nameAbt,kuerzelAbt);


    GridPane grid = new GridPane();
    grid.setVgap(20);
    grid.setPadding(new Insets(0, 0, 0, 0));
    grid.add(sachgebietTable,0,0);
    Pane a = new Pane();
    a.setPrefWidth(20);
    grid.addColumn(1,a);
    grid.add(abteilungTable,2,0);


    Scene secondScene = new Scene(grid);

    // New window (Stage)
    Stage newWindow = new Stage();
    newWindow.setTitle("Second Stage");
    newWindow.setScene(secondScene);

    // Set position of second window, related to primary window.
    newWindow.setX(primaryStage.getX() + 200);
    newWindow.setY(primaryStage.getY() + 100);

    newWindow.show();


  }
}
