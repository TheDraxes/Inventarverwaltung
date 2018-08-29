package TestKlassen;

import Data.*;

import java.util.ArrayList;
import java.util.Date;

public class AssetList {
  private ArrayList<Asset> assets = new ArrayList();
  public AssetList(){
    Fuhrpark a = new Fuhrpark();a.setBezeichnung("BMW E3");a.setAnzahl(1);a.setAnschaffungswert(20000);a.setKw(200);a.setKilometerstand(20000);a.setFahrgestellnummer(982375235);a.setKennzeichen("SN-FP-20");
    a.setInventarnummer(0);
    a.setInserierungsdatum(new Date());
    assets.add(a);

    a = new Fuhrpark();a.setBezeichnung("Mercedes ML 350");a.setAnzahl(1);a.setAnschaffungswert(60000);a.setKw(220);a.setKilometerstand(20000);a.setFahrgestellnummer(98345235);a.setKennzeichen("SN-FP-21");
    a.setInventarnummer(1);
    a.setInserierungsdatum(new Date());
    assets.add(a);

    a = new Fuhrpark();a.setBezeichnung("Citroén DS3");a.setAnzahl(1);a.setAnschaffungswert(12000);a.setKw(89);a.setKilometerstand(40000);a.setFahrgestellnummer(98345235);a.setKennzeichen("SN-FP-22");
    a.setInventarnummer(2);
    a.setInserierungsdatum(new Date());
    assets.add(a);

    a = new Fuhrpark();a.setBezeichnung("Ford Focus");a.setAnzahl(1);a.setAnschaffungswert(8000);a.setKw(80);a.setKilometerstand(20000);a.setFahrgestellnummer(98345235);a.setKennzeichen("SN-FP-23");
    a.setInventarnummer(3);
    a.setInserierungsdatum(new Date());
    assets.add(a);

    a = new Fuhrpark();a.setBezeichnung("Mercedes S-Klasse");a.setAnzahl(1);a.setAnschaffungswert(130000);a.setKw(300);a.setKilometerstand(20000);a.setFahrgestellnummer(98345235);a.setKennzeichen("SN-FP-100");
    a.setInventarnummer(4);
    a.setInserierungsdatum(new Date());
    assets.add(a);

    a = new Fuhrpark();a.setBezeichnung("Ford Mustang GT");a.setAnzahl(1);a.setAnschaffungswert(53000);a.setKw(320);a.setKilometerstand(20000);a.setFahrgestellnummer(98345235);a.setKennzeichen("SN-FP-110");
    a.setInventarnummer(5);
    a.setInserierungsdatum(new Date());
    assets.add(a);

    Mobiliar b = new Mobiliar();b.setBezeichnung("Stuhl");b.setAnzahl(100);b.setAnschaffungswert(40);b.setRaum(223);
    b.setInventarnummer(6);
    b.setInserierungsdatum(new Date());
    assets.add(b);

    b = new Mobiliar();b.setBezeichnung("Tisch");b.setAnzahl(100);b.setAnschaffungswert(40);b.setRaum(223);
    b.setInventarnummer(7);
    b.setInserierungsdatum(new Date());
    assets.add(b);

    b = new Mobiliar();b.setBezeichnung("Schrank");b.setAnzahl(100);b.setAnschaffungswert(40);b.setRaum(223);
    b.setInventarnummer(8);
    b.setInserierungsdatum(new Date());
    assets.add(b);

    Hardware c = new Hardware();c.setBezeichnung("Nivdia GTX 1080");c.setAnzahl(2);c.setAnschaffungswert(655);c.setRaum(223);
    c.setInventarnummer(9);
    c.setInserierungsdatum(new Date());
    assets.add(c);

    c = new Hardware();c.setBezeichnung("Intel i7 3,8GHz");c.setAnzahl(100);c.setAnschaffungswert(360);c.setRaum(223);
    c.setInventarnummer(9);
    c.setInserierungsdatum(new Date());
    assets.add(c);

    Software d = new Software();;d.setBezeichnung("Intellij");d.setAnzahl(2);d.setAnschaffungswert(655);d.setVersion("V2.2.3a");
    d.setInventarnummer(10);
    d.setInserierungsdatum(new Date());
    assets.add(d);

    d = new Software();;d.setBezeichnung("Minecraft");d.setAnzahl(2);d.setAnschaffungswert(23.99);d.setVersion("V2.2.3a");
    d.setInventarnummer(11);
    d.setInserierungsdatum(new Date());
    assets.add(d);

  }

  public ArrayList<Asset> getAssets() {
    return assets;
  }
}
