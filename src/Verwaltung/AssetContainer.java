package Verwaltung;

import Data.*;

import java.io.*;
import java.util.*;

public class AssetContainer implements Serializable{
    private ArrayList<Asset> assetList = new ArrayList<Asset>();
    private long id = 0;
    private String[] existingAssetTypes = {"Boden und Gebäude", "Fuhrpark", "Hardware", "Mobiliar", "Software", "Sonstiges"};

    // Eingabe der Inventarnummer id, Rückgabe des zugehörigen Assets
    public Asset getItemById(long id) {
        Iterator<Asset> it = assetList.iterator();
        while (it.hasNext()) {
            Asset i = it.next();
            if(i.getInventarnummer() == id) {
                return i;
            }
        }
        return null;
    }

    // Veränderung eines Assets mit der Inventarnummer id
    public void editItemById(long id, Asset a) {
        for(int i = 0; i < assetList.size(); i++){
          if(assetList.get(i).getInventarnummer() == id){
            assetList.set(i,a);
          }
        }
    }

    // neues Asset einfügen, Inventarnummer wird automatisch generiert
    public void insertAsset(Asset a) {
        id++;
        a.setInventarnummer(id);

        assetList.add(a);
        System.out.println("**Item hinzugefügt");
    }

    // Asset a löschen
    public void deleteAsset(Asset a) {
        assetList.remove(a);
        System.out.println("**Item entfernt");
    }

    // alle Assets ausgeben (Konsole) - für Testzwecke
    public void showAll() {
        System.out.println("**Ausgabe aller Items");
        Iterator<Asset> it = assetList.iterator();
        while (it.hasNext()) {
            it.next().display();
            System.out.println("- - - - - - - - - - - - - - - - - - -");
        }
    }

    // Inventar speichern
    public void safeInventar(String path){
        System.out.print("**Speichere Inventar");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this);
            System.out.println("**Inventar abgespeichert in " + path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Inventar laden
    public AssetContainer loadInventar(String path) {
        System.out.print("**Lade Inventar");
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            AssetContainer loaded = (AssetContainer) inputStream.readObject();

            System.out.println("**Inventar geladen!");
            return loaded;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Filter
    public ArrayList<Asset> getAssetsByFilter(boolean[] filter) {
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
                filteredList.addAll(getAllFuhrpark());
            }

            return filteredList;
        } catch (Exception e) {
            return null;
        }
    }
    private ArrayList<BodenUndGebaeude> getAllBodenUndGebaeude() {
        ArrayList<BodenUndGebaeude> newList = new ArrayList<BodenUndGebaeude>();

        for(int i = 0; i < assetList.size(); i++){
            if(assetList.get(i).getClass() == BodenUndGebaeude.class){
                newList.add((BodenUndGebaeude) assetList.get(i));
            }
        }
        return newList;
    }
    private ArrayList<Fuhrpark> getAllFuhrpark() {
        ArrayList<Fuhrpark> newList = new ArrayList<Fuhrpark>();

        for(int i = 0; i < assetList.size(); i++){
            if(assetList.get(i).getClass() == Fuhrpark.class){
                newList.add((Fuhrpark) assetList.get(i));
            }
        }
        return newList;
    }
    private ArrayList<Hardware> getAllHardware() {
        return null;
    }
    private ArrayList<Mobiliar> getAllMobiliar() {
        return null;
    }
    private ArrayList<Software> getAllSoftware() {
        ArrayList<Software> newList = new ArrayList<Software>();

        for(int i = 0; i < assetList.size(); i++){
            if(assetList.get(i).getClass() == Software.class){
                newList.add((Software) assetList.get(i));
            }
        }
        return newList;
    }
    private ArrayList<Sonstiges> getAllSonstiges() {
        return null;
    }

    public ArrayList<Asset> getAssetList(){
        return this.assetList;
    }

    public String[] getExistingAssetTypes() {
        return existingAssetTypes;
    }

}
