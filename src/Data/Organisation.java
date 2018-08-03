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

  public Person getLeiter() {
    return leiter;
  }

  public void setLeiter(Person leiter) {
    this.leiter = leiter;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getKürzel() {
    return kürzel;
  }

  public void setKürzel(String kürzel) {
    this.kürzel = kürzel;
  }
}
