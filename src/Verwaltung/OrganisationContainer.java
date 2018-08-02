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
 * @version 1.0
 */

public class OrganisationContainer implements Serializable {
    private ArrayList<Abteilung> AbteilungsList = new ArrayList<Abteilung>();
    private int anzahlAbt = 0;
    private int anzahlSach = 0;


    public OrganisationContainer(){
        Person testPerson = new Person();
        testPerson.setAdmin(true);
        testPerson.setName("Tim");
        testPerson.setSurname("Vinzing");
        Abteilung testA = new Abteilung(testPerson, "E-Government", "GE");
        Abteilung testB = new Abteilung(testPerson, "Fachapplikationen", "FA");

        Sachgebiet testC = new Sachgebiet(testPerson, "E-Government Entwicklung", "GEW", testA);
        Sachgebiet testD = new Sachgebiet(testPerson, "Fachapplikationen Justiz", "FAJ", testB);

        testA.addSachgebiet(testC);
        testB.addSachgebiet(testD);

        AbteilungsList.add(0, testA);
        anzahlAbt++;
        AbteilungsList.add(1, testB);
        anzahlAbt++;
    }

    public boolean insertAbteilung(Abteilung a) {
        if(true){
            System.out.println("[INFO] Organisation angelegt");
            anzahlAbt++;
            return true;
        } else {
            System.out.println("[WARNING] Fehler");
            return false;
        }
    }

    public boolean insertSachgebiet(Sachgebiet a) {
        if(true){
            System.out.println("[INFO] Organisation angelegt");
            anzahlSach++;
            return true;
        } else {
            System.out.println("[WARNING] Fehler");
            return false;
        }
    }

    public String[] getSachgebietNames(){
      String[] sachgebietNames = new String[0];


      return sachgebietNames;
    }

  public ArrayList<Abteilung> getAbteilungsList() {
    return AbteilungsList;
  }

  public void setAbteilungsList(ArrayList<Abteilung> abteilungsList) {
    AbteilungsList = abteilungsList;
  }

  public int getAnzahlAbt() {
    return anzahlAbt;
  }

  public void setAnzahlAbt(int anzahlAbt) {
    this.anzahlAbt = anzahlAbt;
  }

  public int getAnzahlSach() {
    return anzahlSach;
  }

  public void setAnzahlSach(int anzahlSach) {
    this.anzahlSach = anzahlSach;
  }
}
