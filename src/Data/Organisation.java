package Data;

public class Organisation {
  protected Person leiter;
  protected String name;
  protected String kürzel;

  public Organisation(Person leiter, String name, String kürzel){
    this.leiter = leiter;
    this.name = name;
    this.kürzel = kürzel;
  }
  public Organisation(){}


}
