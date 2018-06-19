package Verwaltung;

import Data.Item;
import Data.Person;

import java.io.*;
import java.util.*;

public class ItemContainer implements Serializable{
    private ArrayList<Item> ItemList = new ArrayList<Item>();
    private long id = 0;
    private String[] existingItemTypes = {"Boden und Gebäude", "Fuhrpark", "Hardware", "Mobiliar", "Software", "Sonstiges"};

    public Item getItemById(long id) {
        Iterator<Item> it = ItemList.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if(i.getInventarnummer() == id) {
                return i;
            }
        }
        return null;
    }

    public void editItem(Item i) {

    }



    public void insertItem(Item i) {
        id++;
        i.setInventarnummer(id);

        ItemList.add(i);
        System.out.println("**Item hinzugefügt");
    }

    public void deleteItem(Item i) {
        ItemList.remove(i);
        System.out.println("**Item entfernt");
    }

    public void showAll() {
        System.out.println("**Ausgabe aller Items");
        Iterator<Item> it = ItemList.iterator();
        while (it.hasNext()) {
            it.next().display();
        }
    }

    public ArrayList<Item> getItemList(){
        return this.ItemList;
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

    public ItemContainer loadInventar(String path) {
        System.out.print("**Lade Inventar");
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(path));
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            ItemContainer loaded = (ItemContainer) inputStream.readObject();

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

    public String[] getExistingItemTypes() {
        return existingItemTypes;
    }
}
