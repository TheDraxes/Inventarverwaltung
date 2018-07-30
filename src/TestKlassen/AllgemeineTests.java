package TestKlassen;

public class AllgemeineTests {
  public static void main(String[]args){
    final java.text.DecimalFormatSymbols germany
            = new java.text.DecimalFormatSymbols( new java.util.Locale( "de", "DE" ));
    final java.text.DecimalFormat german
            = new java.text.DecimalFormat( "##,###.00", germany );

    System.out.println(german.format(3000));
    String a = german.format(3000) + " €";
    System.out.println(a);

  }
}
