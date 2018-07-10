package TestKlassen;

import Data.Asset;
import Data.Fuhrpark;
import Verwaltung.AssetContainer;

public class AssetContainerTest {
    public static void main(String [] args){

        /*Fuhrpark a = new Fuhrpark();
        System.out.println(a.getParamAnzahl());
        String[] b = new Fuhrpark().getParamNames();
        System.out.println(b[12]);*/


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


        AssetContainer myContainer = new AssetContainer();

        myContainer.insertAsset(auto1);
        myContainer.insertAsset(auto2);

        myContainer.showAll();

        Fuhrpark auto3 = auto1;

        auto3.setPs(155);
        auto3.setAnzahl(100);

        myContainer.editItemById(1, auto3);

        myContainer.showAll();



    }
}
