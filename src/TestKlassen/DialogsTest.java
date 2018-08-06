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



    }
}
