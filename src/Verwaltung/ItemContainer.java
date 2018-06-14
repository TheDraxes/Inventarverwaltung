package Verwaltung;

import Data.Item;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class ItemContainer {
    private ArrayList<Item> ItemList = new ArrayList<Item>();

    public void insertItem(Item i) {
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

        /*for(int i = 0; i < ItemList.size(); i++){
            ItemList.showItem();
        }*/
        System.out.println();
    }
}
