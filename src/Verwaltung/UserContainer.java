package Verwaltung;

import Data.Person;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class UserContainer implements Serializable {
    private ArrayList<Person> userData = new ArrayList<Person>();
    private int numberOfUser = 0;

    public ArrayList<Person> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<Person> userData) {
        this.userData = userData;
    }

    public int getNumberOfUser() {
        return numberOfUser;
    }

    public void setNumberOfUser(int numberOfUser) {
        this.numberOfUser = numberOfUser;
    }

    public void insertUser(Person p) {
        userData.add(p);
        numberOfUser++;


        System.out.println("**User " + p.getUsername() + " erstellt");
        safeUserData();
    }

    public boolean userIsDuplicate(String username){
        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            if(it.next().getUsername().equals(username))
                return true;
        }
        return false;
    }

    public void safeUserData(){
        System.out.print("**Speichere Inventar");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("user.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this);
            System.out.println("**Inventar abgespeichert in user.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserContainer loadUserData(){
        FileInputStream fileInputStream = null;
        File userLogins = new File("user.dat");
        UserContainer loaded = new UserContainer();
        if(userLogins.exists()) {
            try {
                fileInputStream = new FileInputStream(userLogins);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                loaded = (UserContainer) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return loaded;
    }

    public Person getPersonByUsername(String username) {
        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            Person p = it.next();
            if(p.getUsername().equals(username)) {
                return p;
            }
        }
        return null;
    }

    public String[] getUserNames() {
        String[] usernames = new String[numberOfUser];
        int i = 0;

        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            usernames[i] = it.next().getUsername();
            i++;
        }
        return usernames;
    }

    public boolean userExisting(String username) {
        if(getPersonByUsername(username) != null) {
            return true;
        }
        return false;
    }

    public void deleteUser(String username){

        setLookAndFeel();

        Person p = getPersonByUsername(username);

        if(p != null) {
            int result = JOptionPane.showConfirmDialog(null,"User " + username + " wirklich löschen?");

            if(result == JOptionPane.OK_OPTION) {
                userData.remove(p);
            } else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.NO_OPTION){
                System.out.println("**Vorgang abgebrochen!");
            }

        } else
            System.out.println("**Username existiert nicht!");

        safeUserData();
    }

    public boolean checkLogin(String username, String pw){
        Person p = getPersonByUsername(username);

        if(p != null) {
            if(p.getPassword().equals(pw)) {
                System.out.println("**Eingeloggt als " + p.getUsername());
                return true;
            } else {
                System.out.println("**Benutzername existiert nicht oder Passwort ist falsch!");
                return false;
            }
        }
        System.out.println("**Benutzername existiert nicht oder Passwort ist falsch!");
        return false;
    }

    public void printAllUser(){
        System.out.println("**Ausgabe aller User");
        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            it.next().display();
            System.out.println("====================================================");
        }
    }

    private void setLookAndFeel(){
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
