package Data;

import java.io.Serializable;


/**
 *
 *
 * @version 1.0
 */
public class Organisation implements Serializable{
  protected Person leiter;
  protected String name;
  protected String kuerzel;

  public Organisation(Person leiter, String name, String kuerzel){
    this.leiter = leiter;
    this.name = name;
    this.kuerzel = kuerzel;
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

  public String getKuerzel() {
    return this.kuerzel;
  }

  public void setKuerzel(String kuerzel) {
    this.kuerzel = kuerzel;
  }
}
