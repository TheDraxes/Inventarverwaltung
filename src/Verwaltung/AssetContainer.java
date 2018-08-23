package Verwaltung;

import Data.*;

import java.io.*;
import java.util.*;

/**
 * AssetContainer stellt ein ganzes Inventar dar, in welches alle
 * Assets eingetragen werden können. Weiterhin wird in diesem die
 * Inventarnummer eines Assets automatisch generiert (ID).
 *
 * @author mixd
 *
 * @version 1.0
 */

public class AssetContainer implements Serializable {
    private ArrayList<Asset> assetList = new ArrayList<Asset>();
    private long id = 1;
    private String[] existingAssetTypes = {"Boden und Gebäude", "Fuhrpark", "Hardware", "Mobiliar", "Software", "Sonstiges"};

    /**
     * getAssetById sucht ein Objekt Asset anhand seiner Inventarnummer
     *
     * @param id Inventarnummer nach welcher gesucht wird
     * @return Asset mit der Inventarnummer id
     * @author mixd
     */
    public Asset getAssetById(long id) {
        System.out.println("[INFO] Asset mit der Inventarnummer " + id + " suchen");
        try {
            Iterator<Asset> it = assetList.iterator();
            while (it.hasNext()) {
                Asset i = it.next();
                if(i.getInventarnummer() == id) {
                    System.out.println("[INFO] Asset gefunden");
                    return i;
                }
            }
            System.out.println("[WARNING] Inventarnummer konnte nicht gefunden werden");
            return null;
        } catch(Exception e) {
            System.out.println("[ERROR] Fehler beim Suchen des Assets");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * editItemById editiert ein Asset anhand seiner Inventarnummer
     *
     * @param id Inventarnummer welche editiert werden soll
     * @param a Asset mit neuen Parametern
     * @return true wenn das editieren erfolgreich war
     * @author mixd
     */
    public boolean editItemById(long id, Asset a) {
        System.out.println("[INFO] Asset editieren...");
        try {
            for(int i = 0; i < assetList.size(); i++){
                if(assetList.get(i).getInventarnummer() == id){
                    assetList.set(i,a);
                    System.out.println("[INFO] Asset erfolgreich editiert");
                    return true;
                }
            }
            System.out.println("[WARNING] Inventarnummer konnte nicht gefunden werden");
            return false;
        } catch(Exception e) {
            System.out.println("[ERROR] Fehler beim Editieren des Assets");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * insertAsset fügt ein neues Asset hinzu
     *
     * @param a Asset, welches eingefügt werden soll
     * @return true wenn hinzufügen erfolgreich war
     * @author mixd
     */
    public boolean insertAsset(Asset a) {
        System.out.println("[INFO] Asset hinzufügen...");
        try {
            a.setInventarnummer(id);
            if(assetList.add(a)) {
                System.out.println("[INFO] Asset erfolgreich hinzugefügt");
                id++;
                return true;
            } else
                System.out.println("[ERROR] Fehler beim hinzufügen des Assets");
            return false;
        } catch(Exception e) {
            System.out.println("[ERROR] Fehler beim hinzufügen des Assets");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * insertAsset löscht ein Asset aus dem Container
     *
     * @param a Asset, welches gelöscht werden soll
     * @return true wenn Löschung erfolgreich war
     * @author mixd
     */
    public boolean deleteAsset(Asset a) {
        System.out.println("[INFO] Asset löschen...");
        try {
            if(assetList.contains(a)) {
                assetList.remove(a);
                System.out.println("[INFO] Asset erfolgreich gelöscht");
                return true;
            } else
                System.out.println("[WARNING] Asset existiert nicht");
                return false;
        } catch(Exception e) {
            System.out.println("[ERROR] Fehler beim löschen des Assets");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * safeInventar speichert ein Inventar
     *
     * @param path Pfad in an dem der Container abgespeichert werden soll
     * @return boolean ob das speichern erfolgreich war oder nicht
     * @author mixd
     */
    public boolean safeInventar(String path){
        System.out.println("[INFO] Speichere Inventar...");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(path));
            outputStream = new ObjectOutputStream(fileOutputStream);

            outputStream.writeLong(this.id);
            outputStream.writeObject(this.assetList);

            outputStream.close();
            fileOutputStream.close();

            System.out.println("[INFO] Inventar gespeichert unter '" + path + "'!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("[ERROR] Fehler beim speichern des Inventars!");
        return false;
    }

    /**
     * loadInventar liest ein Inventar aus
     *
     * @param path Speicherpfad des auszulesenden Containers
     * @return geladener AssetContainer
     * @author mixd
     */
    public AssetContainer loadInventar(String path) {
        System.out.println("[INFO] Lade Inventar...");
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(path));
            objectInputStream = new ObjectInputStream(fileInputStream);

            long id = (long) objectInputStream.readLong();
            ArrayList<Asset> list = (ArrayList<Asset>) objectInputStream.readObject();
            AssetContainer loaded = new AssetContainer();

            loaded.setId(id);
            loaded.setAssetList(list);

            fileInputStream.close();
            objectInputStream.close();

            System.out.println("[INFO] Inventar geladen!");
            return loaded;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("[ERROR] Fehler beim laden des Inventars!");
        return null;
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
     * getAssetsByFilter filtert den Container nach Objekten
     *
     * @param filter Array aus boolean Werten
     *               wenn true soll Objekt angezeigt werden, wenn false nicht
     *               filter[0] = BodenUndGebaeude
     *               filter[1] = Fuhrpark
     *               filter[2] = Hardware
     *               filter[3] = Mobiliar
     *               filter[4] = Software
     *               filter[5] = Sonstiges
     * @return Liste mit gefilterten Assets
     * @author mixd
     */
    public ArrayList<Asset> getAssetsByFilter(boolean[] filter) {
        System.out.println("[INFO] Filter anwenden...");
        try {
            ArrayList<Asset> filteredList = new ArrayList<Asset>();
            if(filter[0]) {
                filteredList.addAll(getAllBodenUndGebaeude());
            }
            if(filter[1]) {
                filteredList.addAll(getAllFuhrpark());
            }
            if(filter[2]) {
                filteredList.addAll(getAllHardware());
            }
            if(filter[3]) {
                filteredList.addAll(getAllMobiliar());
            }
            if(filter[4]) {
                filteredList.addAll(getAllSoftware());
            }
            if(filter[5]) {
                filteredList.addAll(getAllSonstiges());
            }

            System.out.println("[INFO] Filterung erfolgreich!");
            return filteredList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR] Filterung fehlgeschlagen!");
        return null;
    }

    /**
     * getAllBodenUndGebaeude gibt alle Assets des Objekts BodenUndGebaeude zurück
     *
     * @return Liste mit allen Assets des Objekts BodenUndGebaeude
     * @author mixd
     */
    private ArrayList<BodenUndGebaeude> getAllBodenUndGebaeude() {
        System.out.println("[INFO] nach BodenUndGebaeude filtern...");
        try {
            ArrayList<BodenUndGebaeude> newList = new ArrayList<BodenUndGebaeude>();

            for(int i = 0; i < assetList.size(); i++){
                if(assetList.get(i).getClass() == BodenUndGebaeude.class){
                    newList.add((BodenUndGebaeude) assetList.get(i));
                }
            }
            System.out.println("[INFO] Filterung nach BodenUndGebaeude erfolgreich!");
            return newList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Fehler bei der Filterung nach BodenUndGebaeude!");
        return null;
    }
    /**
     * getAllFuhrpark gibt alle Assets des Objekts Fuhrpark zurück
     *
     * @return Liste mit allen Assets des Objekts Fuhrpark
     * @author mixd
     */
    private ArrayList<Fuhrpark> getAllFuhrpark() {
        System.out.println("[INFO] nach Fuhrpark filtern...");
        try {
            ArrayList<Fuhrpark> newList = new ArrayList<Fuhrpark>();

            for(int i = 0; i < assetList.size(); i++){
                if(assetList.get(i).getClass() == Fuhrpark.class){
                    newList.add((Fuhrpark) assetList.get(i));
                }
            }
            System.out.println("[INFO] Filterung nach Fuhrpark erfolgreich!");
            return newList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Fehler bei der Filterung nach Fuhrpark!");
        return null;
    }
    /**
     * getAllHardware gibt alle Assets des Objekts Hardware zurück
     *
     * @return Liste mit allen Assets des Objekts Hardware
     * @author mixd
     */
    private ArrayList<Hardware> getAllHardware() {
        System.out.println("[INFO] nach Hardware filtern...");
        try {
            ArrayList<Hardware> newList = new ArrayList<Hardware>();

            for(int i = 0; i < assetList.size(); i++){
                if(assetList.get(i).getClass() == Hardware.class){
                    newList.add((Hardware) assetList.get(i));
                }
            }
            System.out.println("[INFO] Filterung nach Hardware erfolgreich!");
            return newList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Fehler bei der Filterung nach Hardware!");
        return null;
    }
    /**
     * getAllMobiliar gibt alle Assets des Objekts Mobiliar zurück
     *
     * @return Liste mit allen Assets des Objekts Mobiliar
     * @author mixd
     */
    private ArrayList<Mobiliar> getAllMobiliar() {
        System.out.println("[INFO] nach Mobiliar filtern...");
        try {
            ArrayList<Mobiliar> newList = new ArrayList<Mobiliar>();

            for(int i = 0; i < assetList.size(); i++){
                if(assetList.get(i).getClass() == Mobiliar.class){
                    newList.add((Mobiliar) assetList.get(i));
                }
            }
            System.out.println("[INFO] Filterung nach Mobiliar erfolgreich!");
            return newList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Fehler bei der Filterung nach Mobiliar!");
        return null;
    }
    /**
     * getAllSoftware gibt alle Assets des Objekts Software zurück
     *
     * @return Liste mit allen Assets des Objekts Software
     * @author mixd
     */
    private ArrayList<Software> getAllSoftware() {
        System.out.println("[INFO] nach Software filtern...");
        try {
            ArrayList<Software> newList = new ArrayList<Software>();

            for(int i = 0; i < assetList.size(); i++){
                if(assetList.get(i).getClass() == Software.class){
                    newList.add((Software) assetList.get(i));
                }
            }
            System.out.println("[INFO] Filterung nach Software erfolgreich!");
            return newList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Fehler bei der Filterung nach Software!");
        return null;
    }
    /**
     * getAllSonstiges gibt alle Assets des Objekts Sonstiges zurück
     *
     * @return Liste mit allen Assets des Objekts Sonstiges
     * @author mixd
     */
    private ArrayList<Sonstiges> getAllSonstiges() {
        System.out.println("[INFO] nach Sonstiges filtern...");
        try {
            ArrayList<Sonstiges> newList = new ArrayList<Sonstiges>();

            for(int i = 0; i < assetList.size(); i++){
                if(assetList.get(i).getClass() == Sonstiges.class){
                    newList.add((Sonstiges) assetList.get(i));
                }
            }
            System.out.println("[INFO] Filterung nach Sonstiges erfolgreich!");
            return newList;
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("[INFO] Fehler bei der Filterung nach Sonstiges!");
        return null;
    }

    /**
     * liest alle Inventare ein und speichert alle assets in einer einzigen ArrayList
     *
     * @param path pfad zum speicherort der Inventare
     * @see this#loadForSummary(File)
     * @return Arrayliste mit allen Assets des Unternehmens
     * @author Tim
     */
    public ArrayList<Asset> getSummary(String path){
        File filespath = new File(path);
        File[] files = filespath.listFiles();
        ArrayList summaryList = new ArrayList();

        for(File file : files){
            if(checkExtension(file)){
                summaryList.addAll(loadForSummary(file));
            }
        }

        return summaryList;
    }

    /**
     * Läd alle Inventare die zu einer Bestimmten Abteilung gehören
     *
     * @param abteilung Abteilung dessen Inventare gesucht werden
     * @param path pfad zum speicherort der Inventare
     * @param orgContainer container für die Organisationsstruktur
     * @return ArrayListe mit allen assets der Abteilung
     * @author Tim
     */
    public ArrayList<Asset> getSummaryOf(String abteilung, String path, OrganisationContainer orgContainer){
        File filespath = new File(path);
        File[] files = filespath.listFiles();
        ArrayList summaryList = new ArrayList();

        for(File file : files){
            if(checkExtension(file) && checkSachgebiet(file, orgContainer, abteilung)){
                summaryList.addAll(loadForSummary(file));
            }
        }

        return summaryList;
    }

    /**
     * prüft ob eine Datei eine Inventardatei ist
     *
     * @param file File das geprüft werden soll
     * @return boolean ob es eine Inventardatei ist
     * @author Tim
     */
    private boolean checkExtension(File file){
        return file.getName().endsWith(".Inv");
    }

    /**
     * prüft ob die kürzel vor den Inventaren
     * in der Abteilung die Zusammengefasst werden soll vorkommen
     *
     * @param file Inventar das geprüft werden soll
     * @param orgContainer COntainer für Organisationsstruktur
     * @param Abteilung Abteilung in der nach dem Sachgebiet gesucht wird
     * @return boolean ob das Inventar zu der Abteilung gehört
     * @author Tim
     */
    public boolean checkSachgebiet(File file, OrganisationContainer orgContainer, String Abteilung){
        Abteilung abt = orgContainer.getAbteilungByKuerzel(Abteilung);
        ArrayList<Sachgebiet> sachForAbt = abt.getSachgebiete();

        int firstSpace = file.getName().indexOf(" ");

        for(Sachgebiet sach : sachForAbt){
            if(sach.getKuerzel().equals(file.getName().substring(0, firstSpace))){
                return true;
            }
        }
        return false;
    }

    /**
     * Läd ein Inventar und gibt die Arrayliste mit den Assets zurück
     *
     * @param file Inventar das geladen wird
     * @return Liste
     * @author Tim
     */
    private ArrayList loadForSummary(File file){
        ArrayList list = new ArrayList();
        try {
            FileInputStream fop = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fop);

            long id = ois.readLong();
            list = (ArrayList) ois.readObject();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Konsolenausgabe aller Assets (mit allen Parametern) für Testzwecke
     */
    public void showAll() {
        System.out.println("[INFO] Ausgabe aller Assets");
        Iterator<Asset> it = assetList.iterator();
        while (it.hasNext()) {
            it.next().display();
            System.out.println("- - - - - - - - - - - - - - - - - - -");
        }
    }

    // Getter & Setter
    public ArrayList<Asset> getAssetList() {
        return assetList;
    }
    public void setAssetList(ArrayList<Asset> assetList) {
        this.assetList = assetList;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String[] getExistingAssetTypes() {
        return existingAssetTypes;
    }
}
