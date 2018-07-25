package Verwaltung;

import Data.Abteilung;
import Data.Asset;
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

    // neues Asset einfügen, Inventarnummer wird automatisch generiert (hochgezählt)
    public boolean insertOrganisation(Asset a) {
        if(true){
            System.out.println("[INFO] Organisation angelegt");
            return true;
        } else {
            System.out.println("[WARNING] Fehler");
            return false;
        }
    }

}
