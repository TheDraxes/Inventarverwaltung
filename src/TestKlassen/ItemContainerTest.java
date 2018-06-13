package TestKlassen;

import Item.Fuhrpark;
import Verwaltung.ItemContainer;

public class ItemContainerTest {
    public static void main(String [] args){
        Fuhrpark b = new Fuhrpark();
        b.setItembez("BMW Coupe");

        ItemContainer a = new ItemContainer(b);
        System.out.println(a.getItem(0).getItembez());

        Fuhrpark c = (Fuhrpark) a.getItem(0);
        System.out.println(c.getClassString());

        a.getFuhrpark();
    }
}
