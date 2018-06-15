package Verwaltung;

import Data.Item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        
        System.out.println();
    }
}
