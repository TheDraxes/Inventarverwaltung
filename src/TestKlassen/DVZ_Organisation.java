package TestKlassen;

import Data.Abteilung;
import Data.Person;
import Data.Sachgebiet;
import Verwaltung.OrganisationContainer;

public class DVZ_Organisation {
  public OrganisationContainer org = new OrganisationContainer();

  public DVZ_Organisation(){
    Person leiter = new Person("Leiter", "Test", false, "123", true);

    Sachgebiet GEW = new Sachgebiet();
    GEW.setName("WEbanwendung betireb");
    GEW.setKürzel("GEW");
    GEW.setLeiter(leiter);

    Abteilung GE = new Abteilung();
    GE.setName("E-Government");
    GE.setKürzel("GE");
    GE.setLeiter(leiter);
    Abteilung FA = new Abteilung();
    FA.setName("Fachapplikation");
    FA.setKürzel("FA");
    FA.setLeiter(leiter);
    Abteilung SC = new Abteilung();
    SC.setName("Service-Center-IT");
    SC.setKürzel("SC");
    SC.setLeiter(leiter);
    Abteilung KB = new Abteilung();
    KB.setName("Kunden- und Beschaffungsmanagement");
    KB.setKürzel("KB");
    KB.setLeiter(leiter);
    Abteilung MB = new Abteilung();
    MB.setName("Managementberatung");
    MB.setKürzel("MB");
    MB.setLeiter(leiter);
    Abteilung SD = new Abteilung();
    SD.setName("Service Delivery");
    SD.setKürzel("SD");
    SD.setLeiter(leiter);
    Abteilung US = new Abteilung();
    US.setName("Unternehmenssteuerung");
    US.setKürzel("US");
    US.setLeiter(leiter);

    org.insertAbteilung(KB);
    org.insertAbteilung(MB);
    org.insertAbteilung(GE);
    org.insertAbteilung(FA);
    org.insertAbteilung(SC);
    org.insertAbteilung(SD);
    org.insertAbteilung(US);

    org.insertSachgebiet(GEW,"GE");
  }

  public OrganisationContainer get(){
    return this.org;
  }
}
