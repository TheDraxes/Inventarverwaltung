package TestKlassen;

import Data.Abteilung;
import Data.Organisation;
import Data.Person;
import Data.Sachgebiet;
import Verwaltung.OrganisationContainer;

import java.util.ArrayList;
import java.util.Iterator;

public class OrganisationsContainerTest {
    OrganisationContainer container = new OrganisationContainer();
    public static void main(String[] args) {
        Abteilung a = new Abteilung();
        Abteilung a2 = new Abteilung();
        Person p = new Person("Mix", "Darius", true, "12345", true);
        a.setLeiter(p); a.setKürzel("FA");
        a2.setLeiter(p); a2.setKürzel("GE");

        a.setName("Fachapplikationen");
        Sachgebiet s = new Sachgebiet();
        s.setKürzel("FAJ"); s.setName("Fachapplikation Justiz");
        Sachgebiet s2 = new Sachgebiet();
        s2.setKürzel("FAT"); s2.setName("Fachapplikation Toast");

        OrganisationContainer container = new OrganisationContainer();
        container.insertAbteilung(a);
        container.insertAbteilung(a2);
        container.insertSachgebiet(s, "FA");
        container.insertSachgebiet(s2, "FA");


        String[] abteilungen = container.getAllAbteilungsKürzel();
        for(int i = 0; i < abteilungen.length; i++) {
            System.out.println(abteilungen[i]);
        }

        String[] sachgebiete = container.getAllSachgebietsKuerzel();
        for(int i = 0; i < sachgebiete.length; i++) {
            System.out.println(sachgebiete[i]);
        }

        container.safeOrganisationsData();
        OrganisationContainer loaded = new OrganisationContainer();
        loaded = loaded.loadOrganisationsData();


        abteilungen = loaded.getAllAbteilungsKürzel();
        for(int i = 0; i < abteilungen.length; i++) {
            System.out.println(abteilungen[i]);
        }
        sachgebiete = loaded.getAllSachgebietsKuerzel();
        for(int i = 0; i < sachgebiete.length; i++) {
            System.out.println(sachgebiete[i]);
        }
    }
}
