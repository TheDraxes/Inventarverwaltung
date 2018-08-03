package Verwaltung;

import Data.Abteilung;
import Data.Person;
import Data.Sachgebiet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class OrganisationContainer implements Serializable {
    private ArrayList<Abteilung> abteilungArrayList = new ArrayList<Abteilung>();
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

        abteilungArrayList.add(0, testA);
        anzahlAbt++;
        abteilungArrayList.add(1, testB);
        anzahlAbt++;
    }

    public boolean insertAbteilung(Abteilung a) {
        if(this.abteilungArrayList.add(a)) {
            anzahlAbt++;
            System.out.println("[INFO] Abteilung hinzugefügt");
            return true;
        } else {
            System.out.println("[ERROR] Fehler in insertAbteilung");
            return false;
        }
    }

    public boolean insertSachgebiet(Sachgebiet a, String abteilungsKürzel) {
        Abteilung abteilung = getAbteilungByKürzel(abteilungsKürzel);

        if(true){
            System.out.println("[INFO] Organisation angelegt");
            anzahlSach++;
            return true;
        } else {
            System.out.println("[WARNING] Fehler");
            return false;
        }
    }

    public String[] getAllSachgebietsKuerzel() {
        ArrayList<String> sachgebietNames = new ArrayList<String>();
        Iterator<Abteilung> abteilungInterator = abteilungArrayList.iterator();
        while(abteilungInterator.hasNext()) {
            ArrayList<Sachgebiet> sachgebiete = abteilungInterator.next().getSachgebiete();
            Iterator<Sachgebiet> sachgebietIterator = sachgebiete.iterator();
            for(int i = 0; i < sachgebiete.size(); i++) {
                sachgebietNames.add(sachgebietIterator.next().getKürzel());
            }
        }
        String[] kuerzel = new String[sachgebietNames.size()];
        int i = 0;
        for (String s:sachgebietNames) {
            kuerzel[i] = s;
            i++;
        }
        return kuerzel;
    }

    public Abteilung getAbteilungByKürzel(String k){
        Iterator<Abteilung> abteilungIterator = abteilungArrayList.iterator();
        while(abteilungIterator.hasNext()) {
            Abteilung abteilung = abteilungIterator.next();
            if(abteilung.getKürzel().equals(k)) {
                return abteilung;
            }
        }
        System.out.println("[WARNING] Abteilung mit dem Kürzel " + k + "existiert nicht!");
        return null;
    }

    public String[] getAllAbteilungsKürzel(){
        String[] abteilungen = new String[abteilungArrayList.size()];
        Iterator<Abteilung> abteilungInterator = abteilungArrayList.iterator();
        for(int i = 0; i < abteilungArrayList.size(); i++) {
            abteilungen[i] = abteilungInterator.next().getKürzel();
        }

        return abteilungen;
    }
    public boolean anyAbteilungExisting(){
      return true;
    }

    public ArrayList<Abteilung> getAbteilungArrayList() {
    return abteilungArrayList;
  }

    public void setAbteilungArrayList(ArrayList<Abteilung> abteilungArrayList) {
    this.abteilungArrayList = abteilungArrayList;
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
