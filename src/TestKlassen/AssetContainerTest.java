package TestKlassen;

import Data.Asset;
import Data.BodenUndGebaeude;
import Data.Fuhrpark;
import Data.Software;
import Verwaltung.AssetContainer;

import java.util.ArrayList;

/**
 * AssetContainerTest dient für Tests des AssetContainers.
 *
 */

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
        BodenUndGebaeude haus1 = new BodenUndGebaeude();
        haus1.setBezeichnung("DVZ HAUS G");
        haus1.setBuchwert(20000000);
        haus1.setAnzahl(1);
        Software software1 = new Software();
        haus1.setBezeichnung("Microsoft Office");
        haus1.setBuchwert(200);
        haus1.setAnzahl(200);

        AssetContainer myContainer = new AssetContainer();

        myContainer.insertAsset(auto1);
        myContainer.insertAsset(haus1);
        myContainer.insertAsset(auto2);
        myContainer.insertAsset(software1);
        myContainer.showAll();

        System.out.println("===============FILTER==================");
        boolean[] filter = {true, false, false, false, true ,false};
        ArrayList<Asset> filteredList = myContainer.getAssetsByFilter(filter);
        for(int i = 0; i < filteredList.size(); i++) {
            filteredList.get(i).display();
            System.out.println("-------------------------------------");
        }


        String[] paramNames = {
                "Postleitzahl",
                "Ort",
                "Strasse",
                "Hausnummer",
                "Flächengröße",
                "Besitzer"
        };

        System.out.println(paramNames.length);

    }
}
