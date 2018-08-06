package TestKlassen;

import Data.Abteilung;
import Data.Asset;
import Data.Person;
import Data.Sachgebiet;
import GUI.Dialogs;
import GUI.ViewGUI.NewItemDialogs.AssetDialog;
import Verwaltung.OrganisationContainer;
import Verwaltung.UserContainer;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.sql.ResultSet;
import java.util.Optional;

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


      /*
        Person person = new Person("Vinzing", "Tim", true, "123", true);
        Person person1 = new Person();
        person1.initAdmin();

        UserContainer a = new UserContainer();
        a.insertUser(person1);
        a.insertUser(person);

        a.display();

        Abteilung abteilung = new Abteilung();
        abteilung.setName("Fachapp");
        abteilung.setKürzel("FA");

        OrganisationContainer container = new OrganisationContainer();
        container.insertAbteilung(abteilung);

        Sachgebiet sachgebiet = new Sachgebiet();
        sachgebiet.setName("JAHF");
        sachgebiet.setKürzel("FAJ");
        sachgebiet.setSachgebietsleiter(person);

        Pair c = Dialogs.newSachgebietWindow(container, a, sachgebiet);
        System.out.println(c.getValue());

        Sachgebiet abteilung1 = (Sachgebiet) c.getKey();
        System.out.println(abteilung1.getLeiter().getUsername());

*/

      ObservableList<String> observableList =
              FXCollections.observableArrayList(
                      "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test"
                      , "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test"
                      , "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test"
                      , "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test"
                      , "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test"
                      , "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test"
              );

      ComboBox org = new ComboBox(observableList);
      org.setValue("Test");

      Dialog<String> dialog  = new Dialog();

      ButtonType OK_Button = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
      dialog.getDialogPane().getButtonTypes().addAll(OK_Button, ButtonType.CANCEL);

      GridPane grid = new GridPane();
      grid.setHgap(10);
      grid.setVgap(10);
      grid.setPadding(new Insets(20, 150, 10, 10));

      grid.add(new Label("Inventarname: "), 0, 0);
      grid.add(org, 1, 0);

      dialog.getDialogPane().setContent(grid);

      dialog.setResultConverter(dialogButton -> {
       return null;
      });

      Optional<String> result = dialog.showAndWait();


    }
}
