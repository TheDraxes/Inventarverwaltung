package TestKlassen;

public class AllgemeineTests {
  public static void main(String[]args){
    boolean[] a = new boolean[4];
    Boolean[] b = {true,false,true,false};
    for(int i = 0; i < a.length; i++){
      a[i] = b[i];
    }

    if(a[0]){
      System.out.println(0);
    }

    if(a[1]){
      System.out.println(1);
    }

    if(a[2]){
      System.out.println(2);
    }

    if(a[3]){
      System.out.println(3);
    }

  }
}
