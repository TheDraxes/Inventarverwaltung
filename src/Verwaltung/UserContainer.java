package Verwaltung;

import Data.Person;
import GUI.Dialogs;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * UserContainer verwaltet eine Liste,
 * in welcher alle Nutzer verwaltet werden.
 * Sie wird unter anderem für das Login benötigt wird.
 *
 *
 * @version 1.0
 */

public class UserContainer implements Serializable {
    private ArrayList<Person> userData = new ArrayList<Person>();

    /**
     * insertUser fügt einen neuen Nutzer hinzu
     *
     * @param p User der in den Container eingefügt werden soll
     * @return true wenn die Eintragung erfolgreich war
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
     * changePasswort ändert des Passwortes eines Nutzers
     *
     * @param username Nutzername von dem Nutzer dessen Passwort geändert werden soll
     * @param newPassword Neues Passwort
     * @return true wenn die Änderung erfolgreich war
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
     * editUser ändert einen Nutzer
     *
     * @param editedPerson Ein Person Objekt in dem die geänderten Daten des Editierten benutzers stehen
     * @return true wenn die Änderung erfolgreich war
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
     * @return true wenn die Speicherung erfolgreich war
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
            fileOutputStream.close();
            outputStream.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
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
     * @return geladener UserContainer
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
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("[ERROR] Fehler beim Laden der Nutzerdaten!");
        return null;
    }

    /**
     * getPersonByUsername gibt ein Objekt Person mit dem Nutzernamen username zurück
     *
     * @param username Nutzername des gesuchten Nutzers
     * @return Nutzer der zum parameter username gehört
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
     * @return Array mit allen Nutzernamen
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

    /**
     * getUserNamesWithoutAdmin gibt alle Nutzernamen außer den Standardadmin zurück
     *
     * @return Array mit allen Nutzernamen außer dem Admin
     */
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
     * @param username Nutzername des gesuchten Nutzers
     * @return true wenn der Nutzer mit diesem Nutzernamen existiert
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
     * @param username Nutzername des zu löschenden Nutzers
     */
    public void deleteUser(String username){
        Person p = getPersonByUsername(username);
        userData.remove(p);
        safeUserData();
    }

    /**
     * deleteAllUser löscht alle vorhandenen Nutzer.
     *
     */
    public void deleteAllUser() {
        System.out.println("[INFO] Lösche alle Benutzer");
        userData.clear();
    }

    /**
     * checkLogin prüft, ob der eingebenene Name und das eingegebene
     * Passwort übereinstimmen.
     *
     * @param username Nutzername des Nutzers
     * @param pw Passwort des Nutzers
     * @return true wenn die kombination aus Nutzernamen und Passwort vorhanden ist
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

    /**
     * isBlocked prüft, ob ein Nutzer gesperrt ist
     *
     * @param username Nutzersname des Nutzers der geprüft werden soll
     * @return true wenn der Nutzer gesperrt ist
     */
    public boolean isBlocked (String username){
        Person p = this.getPersonByUsername(username);
        if(p == null) {
            System.out.println("Nutzername existiert nicht!");
        } else {
            return p.isLocked();
        }
        return false;
    }

    /**
     * blockUser blockiert einen Nutzer
     *
     * @param username Nutzername des Nutzers, welcher gesperrt werden soll
     */
    public void blockUser(String username){
        Person p = this.getPersonByUsername(username);
        if(p == null) {
            System.out.println("Nutzername existiert nicht! Nutzer konnte nicht gesperrt werden!");
        } else {
            p.setLocked(true);
            this.editUser(p);
        }
    }

    /**
     * Konsolenausgabe aller Nutzer (mit allen Parametern) für Testzwecke
     */
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

    /**
     * Konsolenausgabe aller Nutzernamen für Testzwecke
     */
    public void displayAllUserName(){
        System.out.println("[INFO] Ausgabe aller Benutzernamen");
        Iterator<Person> it = userData.iterator();
        while (it.hasNext()) {
            System.out.println("        -" + it.next().getUsername());
        }
    }

    // Getter & Setter
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
