package GUI.ViewGUI.Comparators;

import java.util.Comparator;

public class AnschaffungswertComparator implements Comparator<Double> {


  public static String resetFormat(String formatted){
    formatted = formatted.replace(".","");
    formatted = formatted.replace("€","");
    formatted = formatted.replace(",", ".");
    return formatted;
  }

  @Override
  public int compare(Double o1, Double o2) {
    if(o1 < o2){
      return -1;
    }
    if(o1 > o2){
      return 1;
    } else {
      return 0;
    }
  }
}
