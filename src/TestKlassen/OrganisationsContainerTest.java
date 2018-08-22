package TestKlassen;

import Data.Abteilung;
import Data.Organisation;
import Data.Person;
import Data.Sachgebiet;
import Verwaltung.OrganisationContainer;

import java.util.ArrayList;

public class OrganisationsContainerTest {
    OrganisationContainer container = new OrganisationContainer();
    public static void main(String[] args) {
        OrganisationContainer container = new OrganisationContainer();
        container.safeOrganisationsData();

        Person p = new Person("Mix", "Darius", true, "12345", true);
        Abteilung ge = new Abteilung(); ge.setLeiter(p); ge.setName("Entwicklung"); ge.setKuerzel("GE");
        Abteilung fa = new Abteilung(); fa.setLeiter(p); fa.setName("Fachapplikation"); fa.setKuerzel("FA");
        Abteilung mb = new Abteilung(); mb.setLeiter(p); mb.setName("Management"); mb.setKuerzel("MB");
        Sachgebiet faj = new Sachgebiet(); faj.setAbtKuerzel("FA"); faj.setName("Justiz"); faj.setLeiter(p); faj.setKuerzel("FAJ");

        container.insertAbteilung(ge); container.insertAbteilung(fa); container.insertAbteilung(mb);
        container.insertSachgebiet(faj, faj.getAbtKuerzel());

        container.displayAllOrgs();
        container.safeOrganisationsData();

        Sachgebiet gej = new Sachgebiet();
        gej.setKuerzel("GEJ"); gej.setLeiter(faj.getLeiter()); gej.setAbtKuerzel("GE"); gej.setName(faj.getName());

        container.editSachgebiet(faj, gej);

        System.out.println("............................................................................");
        container.displayAllOrgs();
    }
}
