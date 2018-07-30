package Verwaltung;

import Data.Abteilung;
import Data.Asset;
import Data.Person;
import Data.Sachgebiet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 *
 * @author mixd
 *
 * @version 1.0
 */

public class OrganisationContainer implements Serializable {
    private ArrayList<Abteilung> AbteilungsList = new ArrayList<Abteilung>();
    private ArrayList<Sachgebiet> SachgebietsList = new ArrayList<>();


    public OrganisationContainer(){
        Person testPerson = new Person();
        testPerson.setAdmin(true);
        testPerson.setName("Tim");
        testPerson.setSurname("Vinzing");
        Abteilung testA = new Abteilung(testPerson, "E-Government", "GE");
        Abteilung testB = new Abteilung(testPerson, "Fachapplikationen", "FA");

        Sachgebiet testC = new Sachgebiet(testPerson, "E-Government Entwicklung", "GEW", testA);
        Sachgebiet testD = new Sachgebiet(testPerson, "Fachapplikationen Justiz", "FAJ", testB);

        AbteilungsList.add(0, testA);
        AbteilungsList.add(1, testB);

        SachgebietsList.add(0,testC);
        SachgebietsList.add(1,testD);
    }

    // neues Asset einfügen, Inventarnummer wird automatisch generiert (hochgezählt)
    public boolean insertAbteilung(Abteilung a) {
        if(true){
            System.out.println("[INFO] Organisation angelegt");
            return true;
        } else {
            System.out.println("[WARNING] Fehler");
            return false;
        }
    }

    public boolean insertSachgebiet(Sachgebiet a) {
        if(true){
            System.out.println("[INFO] Organisation angelegt");
            return true;
        } else {
            System.out.println("[WARNING] Fehler");
            return false;
        }
    }

}
