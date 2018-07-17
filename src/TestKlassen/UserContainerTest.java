package TestKlassen;

import Data.Person;
import Verwaltung.UserContainer;

/**
 *
 *
 */

public class UserContainerTest {
    public static void main(String[] args){

        UserContainer b = new UserContainer();

        /*Person user1 = new Person();
        Person user2 = new Person();
        Person user3 = new Person();

        user1.setUsername("Draxes");
        user1.setName("Vinzing");
        user1.setSurname("Tim");
        user1.setPassword("123");
        user1.setMan(true);
        user1.setAdmin(true);

        user2.setUsername("Marsl");
        user2.setName("Rudolf");
        user2.setSurname("Marcel");
        user2.setPassword("bockwurst007");
        user2.setMan(true);
        user2.setAdmin(false);

        user3.setUsername("Warmupskillar");
        user3.setName("Poppe");
        user3.setSurname("Marcus");
        user3.setPassword("aliciainlove");
        user3.setMan(false);
        user3.setAdmin(false);

        b.insertUser(user1);
        b.insertUser(user2);
        b.insertUser(user3);

        b.safeUserData();

        UserContainer c = new UserContainer().loadUserData();

        c.display();

        System.out.print("richtiges Login: ");
        System.out.println(c.checkLogin("Draxes", "123"));
        System.out.print("falsches Login: ");
        System.out.println(c.checkLogin("Draxes", "124124"));
        System.out.print("richtiges Login: ");
        System.out.println(c.checkLogin("Marsl", "bockwurst007"));
        System.out.print("falsches Login: ");
        System.out.println(c.checkLogin("marsl", "bockwurst007"));*/


        // TEST FÜR BENUTZERNAMEN GENERIERUNG
        b.deleteAllUser();
        b.safeUserData();

        b.display();

        Person user1 = new Person("Mix", "Tim", true, "bockwurst123", true);
        b.insertUser(user1);
        Person user2 = new Person("Mix", "Tim", true, "bockwurst123", true);
        b.insertUser(user2);
        Person user3 = new Person("Mix", "Tim", true, "bockwurst123", true);
        b.insertUser(user3);
        Person user4 = new Person("Mix", "Tim", true, "bockwurst123", true);
        b.insertUser(user4);
        Person user5 = new Person("Mix", "Tim", true, "bockwurst123", true);
        b.insertUser(user5);
        b.display();
    }
}
