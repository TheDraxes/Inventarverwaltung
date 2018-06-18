package TestKlassen;

import Data.Person;
import Verwaltung.UserContainer;

public class UserContainerTest {
    public static void main(String[] args){

        UserContainer b = new UserContainer();

        Person user1 = new Person();
        Person user2 = new Person();
        Person user3 = new Person();

        user1.setBenutzername("Draxes");
        user1.setName("Vinzing");
        user1.setVorname("Tim");
        user1.setPasswort("123");
        user1.setMan(true);
        user1.setAdmin(true);

        user2.setBenutzername("Marsl");
        user2.setName("Rudolf");
        user2.setVorname("Marcel");
        user2.setPasswort("bockwurst007");
        user2.setMan(true);
        user2.setAdmin(false);

        user3.setBenutzername("Warmupskillar");
        user3.setName("Poppe");
        user3.setVorname("Marcus");
        user3.setPasswort("aliciainlove");
        user3.setMan(false);
        user3.setAdmin(false);

        b.insertUser(user1);
        b.insertUser(user2);
        b.insertUser(user3);

        b.safeUserData();

        UserContainer c = new UserContainer().loadUserData();

        c.printAllUser();

        System.out.print("richtiges Login: ");
        System.out.println(c.checkLogin("Draxes", "123"));
        System.out.print("falsches Login: ");
        System.out.println(c.checkLogin("Draxes", "124124"));
        System.out.print("richtiges Login: ");
        System.out.println(c.checkLogin("Marsl", "bockwurst007"));
        System.out.print("falsches Login: ");
        System.out.println(c.checkLogin("marsl", "bockwurst007"));


    }
}
