package data;

import java.util.ArrayList;

public class ItemContainer {
    private ArrayList<Item> ItemList;
    private String name;

    public ItemContainer(ArrayList<Item> newList){
        this.ItemList = newList;
    }

    public ItemContainer(Item firstItem){
        ItemList.add(firstItem);
    }

    public ArrayList<Item> getItemList() {
        return ItemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        ItemList = itemList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
