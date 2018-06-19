package TestKlassen;

import Data.Fuhrpark;
import Data.Item;
import Verwaltung.ItemContainer;

public class ItemContainerTest {
    public static void main(String [] args){

        Fuhrpark a = new Fuhrpark();
        System.out.println(a.getParamAnzahl());
        String[] b = new Fuhrpark().getParamNames();
        System.out.println(b[12]);

        /*
        Fuhrpark auto1 = new Fuhrpark();
        auto1.setFahrgestellnummer(28741982);
        auto1.setKw(188);
        auto1.setAnzahl(3);
        auto1.setBezeichnung("Mercedes Benz E Klasse");
        auto1.setTnd(10);
        //auto1.display();
        Fuhrpark auto2 = new Fuhrpark();
        auto2.setFahrgestellnummer(124142);
        auto2.setKw(122);
        auto2.setAnzahl(5);
        auto2.setBezeichnung("Audi A3");
        auto2.setTnd(7);


        ItemContainer myContainer = new ItemContainer();

        myContainer.insertItem((Item) auto1);
        myContainer.insertItem((Item) auto2);
        myContainer.showAll();
        System.out.println("====================================");
        myContainer.deleteItem(auto1);
        myContainer.showAll();
        System.out.println("====================================");

        myContainer.safeInventar("C:\\Inventarverwaltungspeicher\\ContainerTestInv.Inv");



        myContainer = myContainer.loadInventar("C:\\Inventarverwaltungspeicher\\ContainerTestInv.Inv");
        myContainer.showAll();
        */

    }
}
