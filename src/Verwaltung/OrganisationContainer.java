package Verwaltung;

import Data.Abteilung;
import Data.Organisation;
import Data.Sachgebiet;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * OrganisationContainer verwaltet eine Liste,
 * in welcher alle Organisationsformen (Abteilungen und Sachgebiete)
 * verwaltet werden
 *
 * @author mixd
 * @version 1.0
 */

public class OrganisationContainer implements Serializable {
    private ArrayList<Abteilung> abteilungArrayList = new ArrayList<Abteilung>();

    /**
     * safeOrganisationsData speichert die Organisationsdaten unter 'organisation.dat'
     *
     * @return boolean ob das speichern erfolgreich war oder nicht
     * @author mixd
     */
    public boolean safeOrganisationsData(){
        System.out.println("[INFO] Speichere Organisationsdaten...");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            fileOutputStream = new FileOutputStream("organisation.dat");
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this.abteilungArrayList);
            System.out.println("[INFO] Organisationsdaten gespeichert unter '" + "organisation.dat" + "'!");
            fileOutputStream.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[ERROR] Fehler beim speichern der Organisationsdaten!");
        return false;
    }

    /**
     * renameInventare ändert alle Inventarnamen nachdem ein Sachgebiet umbenannt wurde
     *
     * @param path Speicherpfad der Inventare
     * @param sachgebietsKuerzelAlt alter Kürzel des Sachgebiets
     * @param sachgebietsKuerzelNeu neuer Kürzel des Sachgebiets
     * @author mixd
     */
    public void renameInventare(String path, String sachgebietsKuerzelAlt, String sachgebietsKuerzelNeu) {
        File filespath = new File(path);
        File[] files = filespath.listFiles();

        for (File f: files) {
            if(f.getName().substring(0, sachgebietsKuerzelAlt.length()).equals(sachgebietsKuerzelAlt)) {
                System.out.println("Alt:" + f.getName());
                System.out.println("Alt:" + f.getPath());
                System.out.println("Neu:" + sachgebietsKuerzelNeu + f.getName().substring(sachgebietsKuerzelAlt.length()));
                System.out.println("Neu:" + path + "\\" + sachgebietsKuerzelNeu + f.getName().substring(sachgebietsKuerzelAlt.length()));
                f.renameTo(new File(path + "\\" + sachgebietsKuerzelNeu + f.getName().substring(sachgebietsKuerzelAlt.length())));
            }
        }
    }


    /**
     * loadOrganisationsData liest die Organisationsdaten ein
     *
     * @return geladenene Organisationsdaten, wenn vorhanden
     * @return null wenn keine Organisationsdaten vorhanden sind
     * @author mixd
     */
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

    /**
     * insertAbteilung fügt eine neue Abteilung hinzu
     *
     * @param neu Abteilung, welche eingetragen werden soll
     * @return true wenn die Eintragung erfolgreich war
     * @author mixd
     */
    public boolean insertAbteilung(Abteilung neu) {
        if(this.abteilungArrayList.add(neu)) {
            System.out.println("[INFO] Abteilung " + neu.getKuerzel() + " hinzugefügt");
            return true;
        } else {
            System.out.println("[ERROR] Fehler in insertAbteilung");
            return false;
        }
    }

    /**
     * insertSachgebiet fügt ein neues Sachgebiet hinzu
     *
     * @param neu Sachgebiet, welches eingetragen werden soll
     * @param abteilungsKuerzel Abteilungskürzel, zu welcher Abteilung das Sachgebiet gehört
     * @return true wenn die Eintragung erfolgreich war
     * @author mixd
     */
    public boolean insertSachgebiet(Sachgebiet neu, String abteilungsKuerzel) {
        Abteilung abteilung = getAbteilungByKuerzel(abteilungsKuerzel);
        if(abteilung.getSachgebiete().add(neu)){
            System.out.println("[INFO] Sachgebiet " + neu.getKuerzel() + "zur Abteilung " + abteilung.getKuerzel() + " hinzugefügt!");
            return true;
        } else {
            System.out.println("[ERROR] Fehler in insertSachgebiet");
            return false;
        }
    }

    /**
     * existingAbteilungName prüft, ob ein Abteilungsname bereits vorhanden ist
     *
     * @param abteilung Abteilungname, welcher überprüft werden soll
     * @return true wenn die Abteilung bereits vorhanden ist
     * @author mixd
     */
    public boolean existingAbteilungName(String abteilung){
          for(Abteilung abt : abteilungArrayList){
                if(abt.getName().equals(abteilung)){
                  return true;
              }
          }
          return false;
    }

    /**
     * existingAbteilungKuerzel prüft, ob ein Abteilskürzel bereits vorhanden ist
     *
     * @param abteilungsKuerzel Abteilungskürzel, welcher überprüft werden soll
     * @return true wenn der Kürzel bereits vorhanden ist
     * @author mixd
     */
    public boolean existingAbteilungKuerzel(String abteilungsKuerzel){
        for(Abteilung abt : abteilungArrayList){
            if(abt.getKuerzel().equals(abteilungsKuerzel)){
                return true;
            }
        }
        return false;
    }

    /**
     * getAllSachgebietsKuerzel liefert alle Sachgebietskürzel zurück
     *
     * @return Array mit allen Sachgebietskürzeln
     * @author mixd
     */
    public String[] getAllSachgebietsKuerzel() {
        ArrayList<String> sachgebietNames = new ArrayList<String>();
        Iterator<Abteilung> abteilungInterator = abteilungArrayList.iterator();
        while(abteilungInterator.hasNext()) {
            ArrayList<Sachgebiet> sachgebiete = abteilungInterator.next().getSachgebiete();
            Iterator<Sachgebiet> sachgebietIterator = sachgebiete.iterator();
            for(int i = 0; i < sachgebiete.size(); i++) {
                sachgebietNames.add(sachgebietIterator.next().getKuerzel());
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

    /**
     * getAbteilungByKuerzel sucht ein Objekt Abteilung anhand seines Kürzels
     *
     * @param abteilungsKuerzel Kürzel nach welchem gesucht wird
     * @return zugehörige Abteilung
     * @author mixd
     */
    public Abteilung getAbteilungByKuerzel(String abteilungsKuerzel){
        Iterator<Abteilung> abteilungIterator = abteilungArrayList.iterator();
        while(abteilungIterator.hasNext()) {
            Abteilung abteilung = abteilungIterator.next();
            if(abteilung.getKuerzel().equals(abteilungsKuerzel)) {
                return abteilung;
            }
        }
        System.out.println("[WARNING] Abteilung mit dem Kürzel " + abteilungsKuerzel + "existiert nicht!");
        return null;
    }

    /**
     * getSachgebietByKuerzel sucht ein Objekt Sachgebiet anhand seines Kürzels
     *
     * @param sachgebietKuerzel Kürzel nach welchem gesucht wird
     * @return zugehöriges Sachgebiet
     * @author mixd
     */
    public Sachgebiet getSachgebietByKuerzel(String sachgebietKuerzel){
        for (Abteilung abteilung: abteilungArrayList) {
            ArrayList<Sachgebiet> sachgebiete = abteilung.getSachgebiete();
            Iterator<Sachgebiet> sachgebietIterator = sachgebiete.iterator();
            while(sachgebietIterator.hasNext()) {
                Sachgebiet sachgebiet = sachgebietIterator.next();
                if(sachgebiet.getKuerzel().equals(sachgebietKuerzel)) {
                    return sachgebiet;
                }
            }
        }
        System.out.println("[WARNING] Sachgebiet mit dem Kürzel " + sachgebietKuerzel + "existiert nicht!");
        return null;
    }

    /**
     * getAllAbteilungsKuerzel liefert alle Abteilungskürzeln zurück
     *
     * @return Array mit allen Abteilungskürzeln
     * @author mixd
     */
    public String[] getAllAbteilungsKuerzel(){
        String[] abteilungen = new String[abteilungArrayList.size()];
        Iterator<Abteilung> abteilungInterator = abteilungArrayList.iterator();
        for(int i = 0; i < abteilungArrayList.size(); i++) {
            abteilungen[i] = abteilungInterator.next().getKuerzel();
        }

        return abteilungen;
    }

    /**
     * anyAbteilungExisting prüft, ob es mind. eine Abteilung gibt
     *
     * @return true wenn Anzahl Abteilungen größer als 0
     * @author mixd
     */
    public boolean anyAbteilungExisting(){
        if(abteilungArrayList.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * anySachgebietExisting prüft, ob es mind. ein Sachgebiet gibt
     *
     * @return true wenn Anzahl Sachgebiete größer als 0
     * @author mixd
     */
    public boolean anySachgebietExisting(){
        for (Abteilung abteilung: abteilungArrayList) {
            if(abteilung.sachgebietExisting())
                return true;
        }
        return false;
    }

    /**
     * getAnzahlAbteilungen liefert die Anzahl aller Abteilungen zurück
     *
     * @return Anzahl aller Abteilungen
     * @author mixd
     */
    public int getAnzahlAbteilungen() {
        return abteilungArrayList.size();
    }

    /**
     * getAnzahlAbteilungen liefert die Anzahl aller Sachgebiete zurück
     *
     * @return Anzahl aller Sachgebiete
     * @author mixd
     */
    public int getAnzahlSachgebiete() {
        int anzahl = 0;
        Iterator<Abteilung> abteilungIterator = abteilungArrayList.iterator();
        while(abteilungIterator.hasNext()) {
            anzahl+=abteilungIterator.next().getSachgebiete().size();
        }
        return anzahl;
    }

    /**
     * editAbteilung editiert eine Abteilung
     *
     * @param alt Abteilung vor der Änderung
     * @param neu Abteilung nach der Änderung
     * @author mixd
     */
    public void editAbteilung(Abteilung alt, Abteilung neu){
        this.abteilungArrayList.set(abteilungArrayList.indexOf(alt), neu);
        if(!alt.getKuerzel().equals(neu.getKuerzel())) {
            ArrayList<Sachgebiet> sachgebiete = alt.getSachgebiete();
            for (Sachgebiet s: sachgebiete) {
                s.setAbtKuerzel(neu.getKuerzel());
            }

        }
    }

    /**
     * editSachgebiet editiert ein Sachgebiet
     *
     * @param alt Sachgebiet vor der Änderung
     * @param neu Sachgebiet nach der Änderung
     * @return true, wenn Änderung erfolgreich war
     * @author mixd
     */
    public boolean editSachgebiet(Sachgebiet alt, Sachgebiet neu){
        Abteilung abtAlt = getAbteilungByKuerzel(alt.getAbtKuerzel());
        Abteilung abtNeu = getAbteilungByKuerzel(neu.getAbtKuerzel());

        for (int i = 0; i < abteilungArrayList.size(); i++) {
            ArrayList<Sachgebiet> sachgebiete = abteilungArrayList.get(i).getSachgebiete();

            for (int j = 0; j < sachgebiete.size(); j++) {
                if(sachgebiete.get(j).equals(alt)) {
                    this.abteilungArrayList.get(i).getSachgebiete().set(j, neu);


                    if(!(alt.getAbtKuerzel().equals(neu.getAbtKuerzel()))) {
                        ArrayList<Sachgebiet> sachgebieteAlt = abtAlt.getSachgebiete();
                        ArrayList<Sachgebiet> sachgebieteNeu = abtNeu.getSachgebiete();

                        for(int k = 0; k < sachgebieteAlt.size(); k++) {
                            if(sachgebieteAlt.get(k).getKuerzel().equals(neu.getKuerzel())) {
                                sachgebieteAlt.remove(k);
                                sachgebieteNeu.add(neu);
                                System.out.println("[EDIT] Zugehörigkeit vom Sachgebiet zur Abteilung geändert!");
                            }
                        }
                    }

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * deleteOrg löscht eine Organisation
     *
     * @param a Organisation, welche gelöscht werden soll
     * @return true, wenn Löschung erfolgreich war
     * @author mixd
     */
    public boolean deleteOrg(Organisation a){
        if (a.getClass() == Abteilung.class) {
            System.out.println("[INFO] Lösche Abteilung " + a.getKuerzel() + " ...");
            if(this.abteilungArrayList.contains(a)) {
                this.abteilungArrayList.remove(a);
                System.out.println("[INFO] Abteilung erfolgreich gelöscht!");
                return true;
            }
        } else if (a.getClass() == Sachgebiet.class) {
            System.out.println("[INFO] Lösche Sachgebiet " + a.getKuerzel() + " ...");
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

    /**
     * Konsolenausgabe aller Organisationen (Abteilungen und Sachgebiete) für Testzwecke
     */
    public void displayAllOrgs(){
        for(Abteilung abteilung : abteilungArrayList){
            abteilung.display();
        }
    }

    /**
     * Getter und Setter
     */
    public ArrayList<Organisation> getAllSachgebiete(){
      ArrayList<Organisation> sachgebiete = new ArrayList<>();

      for(Abteilung abteilung : abteilungArrayList){
        sachgebiete.addAll(abteilung.getSachgebiete());
      }

      return sachgebiete;
    }
    public ArrayList<Abteilung> getAbteilungArrayList() {
        return abteilungArrayList;
    }
    public void setAbteilungArrayList(ArrayList<Abteilung> abteilungArrayList) {
        this.abteilungArrayList = abteilungArrayList;
    }
}
