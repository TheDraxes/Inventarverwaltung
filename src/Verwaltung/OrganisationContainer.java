package Verwaltung;

import Data.Abteilung;
import Data.Person;
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

    public boolean safeUserData(){
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

    public OrganisationContainer loadUserData(){
        System.out.println("[INFO] Suche Organisationsdaten...");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
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

    public boolean insertSachgebiet(Sachgebiet a, String abteilungsKürzel) {
        Abteilung abteilung = getAbteilungByKürzel(abteilungsKürzel);
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
        if(abteilungArrayList.size() != 0) {
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
}
