package TestKlassen;

import Data.Abteilung;
import Data.Organisation;
import Verwaltung.OrganisationContainer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

import java.util.ArrayList;

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

    OrganisationContainer container = new DVZ_Organisation().org;
    ObservableList<Organisation> list = FXCollections.observableArrayList(container.getAbteilungArrayList());
    ObservableList<Organisation> sachList = FXCollections.observableArrayList(getSachgebiete(container));

    TableView<Organisation> sachgebietTable = new TableView();

    TableColumn nameSach = new TableColumn("Sachgebiet");
    nameSach.setPrefWidth(188);
    nameSach.setCellValueFactory(new PropertyValueFactory<>("name"));


    TableColumn kuerzelSach = new TableColumn("Kürzel");
    kuerzelSach.setPrefWidth(100);
    kuerzelSach.setCellValueFactory(new PropertyValueFactory<>("kuerzel"));


    sachgebietTable.getColumns().addAll(nameSach,kuerzelSach);
    sachgebietTable.setItems(sachList);


    TableView<Organisation> abteilungTable = new TableView();

    TableColumn nameAbt = new TableColumn("Abteilung");
    nameAbt.setPrefWidth(188);
    nameAbt.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn kuerzelAbt = new TableColumn("Kürzel");
    kuerzelAbt.setPrefWidth(100);
    kuerzelAbt.setCellValueFactory(new PropertyValueFactory<>("kuerzel"));

    abteilungTable.getColumns().addAll(nameAbt,kuerzelAbt);
    abteilungTable.setItems(list);


    GridPane grid = new GridPane();
    grid.setVgap(20);
    grid.setPadding(new Insets(0, 0, 0, 0));
    grid.setPrefWidth(600);
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

  public ArrayList<Organisation> getSachgebiete(OrganisationContainer container){
    ArrayList<Abteilung> abt = container.getAbteilungArrayList();
    ArrayList<Organisation> sachgebiete = new ArrayList<>();

    for(Abteilung abteilung : abt){
      sachgebiete.addAll(abteilung.getSachgebiete());
    }

    return sachgebiete;
  }
}
