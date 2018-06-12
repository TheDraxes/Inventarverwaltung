package TestKlassen;

import data.Fuhrpark;
import data.Item;
import data.ItemContainer;

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
