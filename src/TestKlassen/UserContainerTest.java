package TestKlassen;

import Verwaltung.UserContainer;

public class UserContainerTest {
    public static void main(String[] args){

        UserContainer b = new UserContainer();

        b.insertUser("Tim","1234");
        b.insertUser("Marsl","1234");
        b.insertUser("Darius","1234");

        b.safeUserData();

        UserContainer a = new UserContainer().loadUserData();

        System.out.println(a.getIndexOfUser("Tim"));

        System.out.println(a.userExisting("sdiushgui"));

    }
}
