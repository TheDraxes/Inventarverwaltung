package Verwaltung;

import Data.Person;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * UserContainer verwaltet eine Nutzerdatenbank,
 * welche unter anderem für das Login benötigt wird.
 *
 * @author mixd
 *
 * @version 1.0
 */

public class UserContainer implements Serializable {
    private ArrayList<Person> userData = new ArrayList<Person>();

    /**
     * insertUser fügt einen neuen Benutzer hinzu
     *
     * @author mixd
     * @version 1.0
     */
    public boolean insertUser(Person p) {
        try {
            userData.add(p);

            if(p.isAdmin())
                System.out.println("[INFO] Neuen Admin mit dem Benutzernamen " + p.getUsername() + " angelegt!");
            else
                System.out.println("[INFO] Neuen User mit dem Benutzernamen " + p.getUsername() + " angelegt!");

            safeUserData();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * Änderung des Passwortes eines Nutzers
     *
     * @param username -> Username von dem Benutzer dessen Passwort geändert werden soll
     * @param newPassword -> Neues Passwort
     * @return boolean ob änderung erfolgreich war
     */

    public boolean changePassword(String username, String newPassword){
        try {
            Person p = getPersonByUsername(username);
            p.setPassword(newPassword);
            System.out.println("[INFO] Passwort des Benutzers " + username + " erfolgreich editiert!");
            safeUserData();
            return true;
        } catch(Exception e) {
            System.out.println("[ERROR] Fehler beim editieren des Benutzers!");
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * Änderung eines Nutzers
     *
     * @param editedPerson -> Ein Person Objekt in dem die geänderten Daten des Editierten benutzers stehen
     * @return boolean ob änderung erfolgreich war
     */

    public boolean editUser(Person editedPerson){
        try {
            Person p = getPersonByUsername(editedPerson.getUsername());
            editedPerson.updateUsername();
            this.userData.set(userData.indexOf(p), editedPerson);
            System.out.println("[INFO] Benutzer erfolgreich editiert!");
            safeUserData();
            return true;
        } catch(Exception e) {
            System.out.println("[ERROR] Fehler beim editieren des Benutzers!");
        }
        return false;
    }


    /**
     * safeUserData speichert die Nutzerdaten unter 'user.dat'
     *
     * @author mixd
     * @version 1.1
     */
    public boolean safeUserData(){
        System.out.println("[INFO] Speichere Nutzerdaten...");
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            fileOutputStream = new FileOutputStream("user.dat");
            outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this.userData);
            System.out.println("[INFO] Nutzerdaten gespeichert unter '" + "user.dat" + "'!");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("[ERROR] Fehler beim speichern der Nutzerdaten!");
        return false;
    }

    /**
     * loadUserData liest die Nutzerdaten ein
     *
     * Falls die Datei user.dat existiert, wird diese eingelesen
     *
     * Falls die Datei user.dat nicht existiert, wird die Datei mit dem
     * Standardadmin neu angelegt!
     *
     * @author mixd
     * @version 1.1
     */
    public UserContainer loadUserData(){
        System.out.println("[INFO] Suche Nutzerdaten...");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            File filename = new File("user.dat");

            if(filename.exists()) {
                System.out.println("[INFO] Nutzerdaten gefunden!");
                System.out.println("[INFO] Lese Nutzerdaten ein...");

                fileInputStream = new FileInputStream(filename);
                objectInputStream = new ObjectInputStream(fileInputStream);
                ArrayList<Person> list = (ArrayList<Person>) objectInputStream.readObject();

                UserContainer loaded = new UserContainer();
                loaded.setUserData(list);

                fileInputStream.close();
                objectInputStream.close();

                System.out.println("[INFO] Nutzerdaten erfolgreich eingelesen!");
                return loaded;
            } else {
                System.out.println("[INFO] Nutzerdaten konnten nicht gefunden werden!");
                UserContainer newUserdata = new UserContainer();
                Person admin = new Person();
                admin.initAdmin();
                newUserdata.insertUser(admin);
                System.out.println("[INFO] Neue Nutzerdaten mit Standardadmin erstellt");
                return newUserdata;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("[ERROR] Fehler beim Laden der Nutzerdaten!");
        return null;
    }

    /**
     * getPersonByUsername gibt ein Objekt Person mit dem Nutzernamen username zurück
     *
     * @author mixd
     * @version 1.0
     */
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

    /**
     * getUserNames gibt alle Nutzernamen zurück
     *
     * @author mixd
     * @version 1.0
     */
    public String[] getUserNames() {
        String[] usernames = new String[userData.size()];
        int i = 0;

        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            usernames[i] = it.next().getUsername();
            i++;
        }
        return usernames;
    }

    public String[] getUserNamesWithoutAdmin() {
        String[] usernames = new String[userData.size()-1];
        int anz = 0;
        for(int i = 0; i < userData.size(); i++){
            if(!(userData.get(i).getUsername().equals("admin"))){
                usernames[anz] = userData.get(i).getUsername();
                anz++;
            }
        }
        return usernames;
    }


    /**
     * userExisting prüft, ob ein Nutzername bereits vorhanden ist
     *
     * @author mixd
     * @version 1.0
     */
    public boolean userExisting(String username) {
        if(getPersonByUsername(username) != null) {
            return true;
        }
        return false;
    }

    /**
     * deleteUser löscht einen Nutzer mithilfe des Nutzernames
     *
     * @author Tim
     * @version 1.0
     */
    public void deleteUser(String username){

        setLookAndFeel();

        Person p = getPersonByUsername(username);

        if(p != null) {
            int result = JOptionPane.showConfirmDialog(null,"Benutzer " + username + " wirklich löschen?");

            if(result == JOptionPane.OK_OPTION) {
                userData.remove(p);
            } else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.NO_OPTION){
                System.out.println("[INFO] Vorgang abgebrochen!");
            }

        } else
            System.out.println("[WARNING] Benutzername existiert nicht!");

        safeUserData();
    }

    /**
     * deleteAllUser löscht alle vorhandenen Benutzer.
     *
     * @author mixd
     * @version 1.0
     */
    public void deleteAllUser() {
        System.out.println("[INFO] Lösche alle Benutzer");
        userData.clear();
    }

    /**
     * checkLogin prüft, ob der eingebenene Name und das eingegebene
     * Passwort übereinstimmen.
     *
     * @author mixd
     * @version 1.0
     */
    public boolean checkLogin(String username, String pw){
        Person p = getPersonByUsername(username);

        if(p != null) {
            if(p.getPassword().equals(pw)) {
                System.out.println("[INFO] Eingeloggt als " + p.getUsername());
                return true;
            } else {
                System.out.println("[WARNING] Benutzername existiert nicht oder Passwort ist falsch!");
                return false;
            }
        }
        System.out.println("[WARNING] Benutzername existiert nicht oder Passwort ist falsch!");
        return false;
    }

    public boolean isBlocked (String username){
        Person p = this.getPersonByUsername(username);
        p.display();
        return p.isLocked();
    }

    public void blockUser(String username){
        Person p = this.getPersonByUsername(username);
        p.setLocked(true);
        this.editUser(p);
    }

    /**
     * setLookAndFeel
     *
     * @author Tim
     * @version 1.0
     */
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

    // Konsolenausgabe aller Nutzer für Testzwecke
    public void display(){
        System.out.println("[INFO] Ausgabe aller Benutzer");
        System.out.println("=============================");
        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            it.next().display();
            if(it.hasNext()) {
                System.out.println("- - - - - - - - - - - - - -");
            }
        }
    }

    // Konsolenausgabe aller Nutzernamen für Testzwecke
    public void displayAllUserName(){
        System.out.println("[INFO] Ausgabe aller Benutzernamen");
        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            System.out.println("        -" + it.next().getUsername());
        }
    }

    // Getter und Setter
    public ArrayList<Person> getUserData() {
        return userData;
    }
    private void setUserData(ArrayList<Person> userData) {
        this.userData = userData;
    }
    public int getNumberOfUser() {
        return userData.size();
    }
}
