package TestKlassen;

import Data.Abteilung;
import Data.Asset;
import Data.Person;
import GUI.Dialogs;
import GUI.ViewGUI.NewItemDialogs.AssetDialog;
import Verwaltung.UserContainer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Pair;

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

        Person person = new Person("Vinzing", "Tim", true, "123", true);
        Person person1 = new Person();
        person1.initAdmin();

        UserContainer a = new UserContainer();
        a.insertUser(person1);
        a.insertUser(person);

        Pair c = Dialogs.newAbteilungWindow(a, null);
        System.out.println(c.getValue());

        Abteilung abteilung = (Abteilung) c.getKey();

        System.out.println(abteilung.getName());

    }
}
