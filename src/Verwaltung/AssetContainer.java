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
    private long id = 0;
    private String[] existingAssetTypes = {"Boden und Gebäude", "Fuhrpark", "Hardware", "Mobiliar", "Software", "Sonstiges"};

    // Eingabe der Inventarnummer id, Rückgabe des zugehörigen Assets
    public Asset getItemById(long id) {
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

    // Veränderung eines Assets mit der Inventarnummer id durch Asset a
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

    // neues Asset einfügen, Inventarnummer wird automatisch generiert (hochgezählt)
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

    // Asset a löschen
    public boolean deleteAsset(Asset a) {
        System.out.println("[INFO] Asset löschen...");
        try {
            if(assetList.contains(a)) {
                assetList.remove(a);
                id--;
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

    // alle Assets ausgeben (Konsole) - für Testzwecke
    public void showAll() {
        System.out.println("[INFO] Ausgabe aller Assets");
        Iterator<Asset> it = assetList.iterator();
        while (it.hasNext()) {
            it.next().display();
            System.out.println("- - - - - - - - - - - - - - - - - - -");
        }
    }

    /**
     * safeInventar speichert ein Inventar unter 'inventarname.inv'
     *
     * @author mixd
     * @version 1.1
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

            System.out.println("[INFO] Inventar gespeichert unter '" + path + "'!");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[ERROR] Fehler beim speichern des Inventars!");
        return false;
    }

    /**
     * loadInventar liest ein Inventar aus 'inventarname.Inv' aus.
     *
     * @author mixd
     * @version 1.1
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

            System.out.println("[INFO] Inventar geladen!");
            return loaded;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
                objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[ERROR] Fehler beim laden des Inventars!");
        return null;
    }

    // Filtermethoden
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

    // Getter und Setter
    public ArrayList<Asset> getAssetList() {
        return assetList;
    }
    public void setAssetList(ArrayList<Asset> assetList) {
        this.assetList = assetList;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String[] getExistingAssetTypes() {
        return existingAssetTypes;
    }
    public void setExistingAssetTypes(String[] existingAssetTypes) {
        this.existingAssetTypes = existingAssetTypes;
    }
}
