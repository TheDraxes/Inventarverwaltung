package Verwaltung;

import Item.Item;

import java.util.ArrayList;

public class ItemContainer {
    private ArrayList<Item> ItemList = new ArrayList<Item>();
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

    public void insertItem(Item newItem){
        ItemList.add(newItem);
        System.out.println("**Item " + newItem.getItembez() + " in den Container eingefügt");
    }

    public Item getItem(int index){
        return ItemList.get(index);
    }

    public ArrayList<Item>getFuhrpark(){
        ArrayList Furpark = new ArrayList();

        for(int i = 0; i < ItemList.size(); i++){
            if(ItemList.get(i).getClass().equals("Fuhrpark")){
                System.out.println(i + " Found");
            }
        }
        return Furpark;
    }
}
