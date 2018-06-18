package Data;

import java.io.Serializable;

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

    public Person() {
        System.out.println("**Neuen Benutzer angelegt!");
    }

    public void initAdmin() {
        this.username = "admin";
        this.password = "123";
        this.isAdmin = true;
    }

    public Person(String name, String surname, boolean isMan, String password, boolean isAdmin) {
        this.name = name;
        this.surname = surname;
        this.isMan = isMan;
        this.password = password;
        this.isAdmin = isAdmin;

        String generatedUsername = new String();
        generatedUsername = name.toLowerCase() + surname.substring(0,1).toLowerCase();

        this.username = generatedUsername;
        if(isAdmin)
            System.out.println("**Neuen Admin mit dem Namen " + username + " angelegt!");
        else
            System.out.println("**Neuen User mit dem Namen " + username + " angelegt!");


    }

    public void display() {
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
        //System.out.println("E-Mail Adresse: " + email);
        System.out.println("Passwort:       " + password);
        System.out.println("Admin:          " + isAdmin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("**Name geändert");
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        System.out.println("**Vorname geändert");
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
        System.out.println("**Geschlecht geändert");
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
        System.out.println("**Raum geändert");
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
        System.out.println("**Telefonnummer geändert");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println("**E-Mail geändert");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println("**Benutzernamen geändert");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("**Passwort geändert");
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        this.isAdmin = admin;
        System.out.println("**Adminzugriff geändert");
    }
}
