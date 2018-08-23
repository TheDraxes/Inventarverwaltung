package Data;

import Verwaltung.UserContainer;

import java.io.Serializable;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */
public class Person implements Serializable {
    private String name;
    private String surname;
    private boolean isMan;
    private int room;
    private long phonenumber;
    private String email;
    private String username;
    private String password;
    private boolean isAdmin;
    private boolean isLocked;

    /**
     * Konstruktor zum Anlegen eines neuen Nutzers ohne Parameter
     */
    public Person() {
        System.out.println("[KONSTRUKTOR] Person ohne Parameter angelegt!");
    }

    /**
     * Konstruktor zum Anlegen eines neuen Nutzers mit Parametern
     */
    public Person(String name, String surname, boolean isMan, String password, boolean isAdmin) {
        this.name = name;
        this.surname = surname;
        this.isMan = isMan;
        this.password = password;
        this.isAdmin = isAdmin;
        this.username = generateUsername();
        this.email = generateEmail();
        System.out.println("[KONSTRUKTOR] Benutzer " + this.username + " angelegt!");
    }

    /**
     * initAdmin erstellt einen Admin mit dem Passwort 123
     *
     * @author mixd
     */
    public void initAdmin() {
        this.username = "admin";
        this.password = "123";
        this.isAdmin = true;
        System.out.println("[INFO] Standardadmin angelegt!");
    }

    /**
     * generateUsername generiert den Nutzernamen des Nutzers
     * Nutzername = Nachname + Anfangsbuchstaben vom Vornamen erstellt
     *
     * @return generierten Nutzernamen
     * @author mixd
     */
    private String generateUsername() {
        UserContainer userData = new UserContainer().loadUserData();
        String generatedUsername = new String();

        for(int i = 1; i <= surname.length(); i++) {
            generatedUsername = name.toLowerCase() + surname.substring(0,i).toLowerCase();
            if(!userData.userExisting(generatedUsername)) {
                return generatedUsername;
            }
        }

        int i = 1;
        while(true) {
            generatedUsername = name.toLowerCase() + surname.substring(0,1).toLowerCase() + i;

            if(!userData.userExisting(generatedUsername)) {
                return generatedUsername;
            } else
                i++;
        }
    }

    /**
     * generateEmail generiert die E-Mail Adresse des Nutzers
     * E-Mail = 'Anfangsbuchstaben des Vornamen' . 'Nachname' @ 'dvz-mv.de'
     *
     * @return generierte E-Mail
     * @author mixd
     */
    private String generateEmail() {
        return username.substring(name.length()) + "." + name.toLowerCase() + "@dvz-mv.de";
    }

    /**
     * updateUsername aktualisiert einen Nutzernamen nach der Änderung des Nutzers
     *
     * @author mixd
     */
    public void updateUsername(){
        UserContainer userData = new UserContainer().loadUserData();
        Person p = userData.getPersonByUsername(this.username);
        userData.deleteUser(this.username);
        userData.insertUser(p);
        /*if(!p.getName().equals(this.name) || !p.getSurname().equals(this.surname)) {
            String newusername = generateUsername();

            if(!this.username.equals(newusername)) {
                System.out.print("[EDIT] Username von " + this.username);
                this.username = newusername;
                System.out.println(" auf " + this.username + " geändert!");

                System.out.print("[EDIT] E-Mail von " + this.email);
                this.email = generateEmail();
                System.out.println(" auf " + this.email + " geändert!");
            }
        }*/
    }

    /**
     * Konsolenausgabe eines Nutzers (mit allen Parametern) für Testzwecke
     */
    public void display() {
        System.out.println("[INFO] DISPLAYMETHODE PERSON");
        if(!this.username.equals("admin")) {
            System.out.println("Benutzername:   " + username);
            System.out.println("--------------------------");
            System.out.println("Vorname:        " + surname);
            System.out.println("Nachname:       " + name);
            System.out.print("Geschlecht:     ");
            if(isMan)
                System.out.println("männlich");
            else
                System.out.println("weiblich");
            //System.out.println("Raum:           " + room);
            //System.out.println("Telefonnummer:  " + phonenumber);
            System.out.println("E-Mail Adresse: " + email);
            System.out.println("Passwort:       " + password);
            System.out.println("Admin:          " + isAdmin);
        } else {
            System.out.println("Benutzername:   " + username);
            System.out.println("--------------------------");
            System.out.println("Passwort:       " + password);
        }
    }

    // Getter & Setter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        System.out.println("[EDIT] Name geändert");
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public boolean isMan() {
        return isMan;
    }
    public void setMan(boolean man) {
        isMan = man;
        System.out.println("[EDIT] Geschlecht geändert");
    }
    public int getRoom() {
        return room;
    }
    public void setRoom(int room) {
        this.room = room;
        System.out.println("[EDIT] Raum geändert");
    }
    public long getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
        System.out.println("[EDIT] Telefonnummer geändert");
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        System.out.println("[EDIT] E-Mail geändert");
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
        System.out.println("[EDIT] Benutzernamen geändert");
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
        System.out.println("[EDIT] Passwort geändert");
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
        System.out.println("[EDIT] Adminzugriff geändert");
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void setLocked(boolean locked) {
        isLocked = locked;
        if(isLocked) {
            System.out.println("[INFO] Benutzer " + this.username + " wurde gesperrt!");
        }
        else
            System.out.println("[INFO] Benutzer " + this.username + " wurde entsperrt!");
    }
}
