package Verwaltung;

import Data.Item;

import java.io.*;
import java.util.*;

public class ItemContainer {
    private ArrayList<Item> ItemList = new ArrayList<Item>();
    private long id = 0;
    private String[] existingItemTypes = {"Boden und Gebäude", "Fuhrpark", "Hardware", "Mobiliar", "Software", "Sonstiges"};

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

    public void safeInventar(String name){
        System.out.print("**Speichere Inventar");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(name + ".dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this);
            System.out.println("**Inventar abgespeichert in " + name + ".dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ItemContainer loadInventar(String name) {
        System.out.print("**Lade Inventar");
        try {
            FileInputStream fileInputStream = new FileInputStream(name + ".dat");
            ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
            ItemContainer loaded = (ItemContainer) inputStream.readObject();

            System.out.println("**Inventar " + name + ".dat geladen!");
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
