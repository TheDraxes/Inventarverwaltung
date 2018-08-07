package Verwaltung;

import Data.Abteilung;
import Data.Organisation;
import Data.Sachgebiet;

import java.io.*;
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

    public OrganisationContainer(){
    }

    public boolean safeOrganisationsData(){
        System.out.println("[INFO] Speichere Organisationsdaten...");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            fileOutputStream = new FileOutputStream("organisation.dat");
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this.abteilungArrayList);
            System.out.println("[INFO] Organisationsdaten gespeichert unter '" + "organisation.dat" + "'!");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[ERROR] Fehler beim speichern der Organisationsdaten!");
        return false;
    }

    public OrganisationContainer loadOrganisationsData(){
        System.out.println("[INFO] Suche Organisationsdaten...");
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            File filename = new File("organisation.dat");

            if(filename.exists()) {
                System.out.println("[INFO] Organisationsdaten gefunden!");
                System.out.println("[INFO] Lese Organisationsdaten ein...");

                fileInputStream = new FileInputStream(filename);
                objectInputStream = new ObjectInputStream(fileInputStream);
                ArrayList<Abteilung> abteilungsList = (ArrayList<Abteilung>) objectInputStream.readObject();

                OrganisationContainer loaded = new OrganisationContainer();
                loaded.setAbteilungArrayList(abteilungsList);

                fileInputStream.close();
                objectInputStream.close();

                System.out.println("[INFO] Organisationsdaten erfolgreich eingelesen!");
                return loaded;
            } else {
                System.out.println("[INFO] Organisationsdaten konnten nicht gefunden werden!");
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR] Fehler beim Laden der Organisationsdaten!");
        return null;
    }

    public boolean insertAbteilung(Abteilung a) {
        if(this.abteilungArrayList.add(a)) {
            System.out.println("[INFO] Abteilung " + a.getKürzel() + " hinzugefügt");
            return true;
        } else {
            System.out.println("[ERROR] Fehler in insertAbteilung");
            return false;
        }
    }

    public boolean insertSachgebiet(Sachgebiet a, String abteilungsKuerzel) {
        Abteilung abteilung = getAbteilungByKuerzel(abteilungsKuerzel);
        if(abteilung.getSachgebiete().add(a)){
            System.out.println("[INFO] Sachgebiet " + a.getKürzel() + "zur Abteilung " + abteilung.getKürzel() + " hinzugefügt!");
            return true;
        } else {
            System.out.println("[ERROR] Fehler in insertSachgebiet");
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

    public Abteilung getAbteilungByKuerzel(String abteilungsKuerzel){
        Iterator<Abteilung> abteilungIterator = abteilungArrayList.iterator();
        while(abteilungIterator.hasNext()) {
            Abteilung abteilung = abteilungIterator.next();
            if(abteilung.getKürzel().equals(abteilungsKuerzel)) {
                return abteilung;
            }
        }
        System.out.println("[WARNING] Abteilung mit dem Kürzel " + abteilungsKuerzel + "existiert nicht!");
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
        if(abteilungArrayList.size() > 0) {
            return true;
        }
        return false;
    }

    public ArrayList<Abteilung> getAbteilungArrayList() {
        return abteilungArrayList;
    }

    public void setAbteilungArrayList(ArrayList<Abteilung> abteilungArrayList) {
        this.abteilungArrayList = abteilungArrayList;
    }

    public int getAnzahlAbteilungen() {
        return abteilungArrayList.size();
    }

    public int getAnzahlSachgebiete() {
        int anzahl = 0;
        Iterator<Abteilung> abteilungIterator = abteilungArrayList.iterator();
        while(abteilungIterator.hasNext()) {
            anzahl+=abteilungIterator.next().getSachgebiete().size();
        }
        return anzahl;
    }

    public void editAbteilung(Abteilung alt, Abteilung neu){
        this.abteilungArrayList.set(abteilungArrayList.indexOf(alt), neu);
    }

    public void displayAllOrgs(){
        for(Abteilung abteilung : abteilungArrayList){
            abteilung.display();
        }
    }

    /**
     *
     * @return -> boolean ob ein Sachgebiet exestiert
     */
    public boolean anySachgebietExisting(){
        for (Abteilung abteilung: abteilungArrayList) {
            if(abteilung.sachgebietExisting())
                return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public Sachgebiet getSachgebietByKuerzel(String sachgebietKuerzel){
        for (Abteilung abteilung: abteilungArrayList) {
            ArrayList<Sachgebiet> sachgebiete = abteilung.getSachgebiete();
            Iterator<Sachgebiet> sachgebietIterator = sachgebiete.iterator();
            while(sachgebietIterator.hasNext()) {
                Sachgebiet sachgebiet = sachgebietIterator.next();
                if(sachgebiet.getKürzel().equals(sachgebietKuerzel)) {
                    return sachgebiet;
                }
            }
        }
        System.out.println("[WARNING] Sachgebiet mit dem Kürzel " + sachgebietKuerzel + "existiert nicht!");
        return null;
    }

    public boolean editSachgebiet(Sachgebiet alt, Sachgebiet neu){
        for (int i = 0; i < abteilungArrayList.size(); i++) {
            ArrayList<Sachgebiet> sachgebiete = abteilungArrayList.get(i).getSachgebiete();

            for (int j = 0; j < sachgebiete.size(); j++) {
                if(sachgebiete.get(j).equals(alt)) {
                    this.abteilungArrayList.get(i).getSachgebiete().set(j, neu);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteOrg(Organisation a){
        if (a.getClass() == Abteilung.class) {
            System.out.println("[INFO] Lösche Abteilung " + a.getKürzel() + " ...");
            if(this.abteilungArrayList.contains(a)) {
                this.abteilungArrayList.remove(a);
                System.out.println("[INFO] Abteilung erfolgreich gelöscht!");
                return true;
            }
        } else if (a.getClass() == Sachgebiet.class) {
            System.out.println("[INFO] Lösche Sachgebiet " + a.getKürzel() + " ...");
            for (int i = 0; i < abteilungArrayList.size(); i++) {
                if(this.abteilungArrayList.get(i).getSachgebiete().contains(a)) {
                    this.abteilungArrayList.get(i).getSachgebiete().remove(a);
                    System.out.println("[INFO] Sachgebiet erfolgreich gelöscht!");
                    return true;
                }
            }
        }
        System.out.println("[WARNING] Abteilung/Sachgebiet konnte nicht gelöscht werden!");
        return false;
    }
}
