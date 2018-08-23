package TestKlassen;

import Data.Asset;
import Data.Fuhrpark;
import Data.Software;

import java.util.ArrayList;

public class AllgemeineTests {
  public static void main(String[]args){

    StringArrayEdit();

  }

  public static void StringArrayEdit(){
    String[] a = {"1", "2", "3"};
    int anz = 0;

    String[] b = new String[a.length-1];

    for (String string : a){
      System.out.println(string);
    }

    for(int i = 0; i < a.length; i++){
      if(a[i].equals("2"))continue;
      b[anz] = a[i];
      anz++;
    }

    a = b;

    for (String string : a){
      System.out.println(string);
    }
  }

  public static String resetFormat(String formatted){
    formatted = formatted.replace(".","");
    formatted = formatted.replace("€","");
    formatted = formatted.replace(",", ".");
    return formatted;
  }

  public static void Formattertest(){
    final java.text.DecimalFormatSymbols germany
            = new java.text.DecimalFormatSymbols( new java.util.Locale( "de", "DE" ));
    final java.text.DecimalFormat german
            = new java.text.DecimalFormat( "##,###.00", germany );

    Fuhrpark b = new Fuhrpark();
    b.setAnschaffungswert(3000);

    System.out.println(german.format(3000));
    String a = german.format(3000) + " €";
    System.out.println(a);

    System.out.println(german.format(b.getAnschaffungswert()));

    a = resetFormat(a);

    Double test = Double.parseDouble(a);

    System.out.println(test);

    Fuhrpark fuhrpark1 = new Fuhrpark();
    fuhrpark1.setBezeichnung("test");

    Fuhrpark fuhrpark2 = new Fuhrpark();
    fuhrpark2.setBezeichnung("test");

    Fuhrpark fuhrpark3 = new Fuhrpark();
    fuhrpark3.setBezeichnung("test");

    Software software1 = new Software();
    software1.setBezeichnung("testSoftware");

    Software software2 = new Software();
    software2.setBezeichnung("testSoftware");

    Software software3 = new Software();
    software3.setBezeichnung("testSoftware");



    ArrayList<Asset> list1 = new ArrayList<>();
    list1.add(fuhrpark1);
    list1.add(fuhrpark2);
    list1.add(fuhrpark3);

    ArrayList<Asset> list2 = new ArrayList<>();
    list2.add(software1);
    list2.add(software2);
    list2.add(software3);


    list1.addAll(list2);

    for(Asset asset : list1){
      asset.display();
    }
  }
}
