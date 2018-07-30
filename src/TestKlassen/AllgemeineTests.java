package TestKlassen;

import Data.Fuhrpark;

public class AllgemeineTests {
  public static void main(String[]args){
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
  }

  public static String resetFormat(String formatted){
    formatted = formatted.replace(".","");
    formatted = formatted.replace("€","");
    formatted = formatted.replace(",", ".");
    return formatted;
  }
}
