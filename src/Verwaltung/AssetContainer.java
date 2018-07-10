package Verwaltung;

import Data.*;

import java.io.*;
import java.util.*;

public class AssetContainer implements Serializable{
    private ArrayList<Asset> assetList = new ArrayList<Asset>();
    private long id = 0;
    private String[] existingAssetTypes = {"Boden und Gebäude", "Fuhrpark", "Hardware", "Mobiliar", "Software", "Sonstiges"};

    //TEST
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

    public void editItemById(long id, Asset a) {
        Iterator<Asset> it = assetList.iterator();
        while (it.hasNext()) {
            Asset i = it.next();
            if(i.getInventarnummer() == id) {
                i = a;
            }
        }
    }

    public void insertAsset(Asset a) {
        id++;
        a.setInventarnummer(id);

        assetList.add(a);
        System.out.println("**Item hinzugefügt");
    }

    public void deleteAsset(Asset a) {
        assetList.remove(a);
        System.out.println("**Item entfernt");
    }

    public void showAll() {
        System.out.println("**Ausgabe aller Items");
        Iterator<Asset> it = assetList.iterator();
        while (it.hasNext()) {
            it.next().display();
            System.out.println("- - - - - - - - - - - - - - - - - - -");
        }
    }

    public ArrayList<Asset> getAssetList(){
        return this.assetList;
    }

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

    public String[] getExistingAssetTypes() {
        return existingAssetTypes;
    }

    // Filter

    public ArrayList<Asset> getFilteredAssets(boolean[] filter) {
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
        return null;
    }
    private ArrayList<Fuhrpark> getAllFuhrpark() {
        return null;
    }
    private ArrayList<Hardware> getAllHardware() {
        return null;
    }
    private ArrayList<Mobiliar> getAllMobiliar() {
        return null;
    }
    private ArrayList<Software> getAllSoftware() {
        return null;
    }
    private ArrayList<Sonstiges> getAllSonstiges() {
        return null;
    }

}
