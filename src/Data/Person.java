package Data;

public class Person {
    private String name;
    private String vorname;
    private boolean isMan;
    private int raum;
    private long telefonnummer;
    private String email;
    private String benutzername;
    private String passwort;
    private boolean admin;

    public Person() {
        System.out.println("**Neuen Benutzer angelegt!");
    }

    public Person(String name, String vorname, boolean isMan, String benutzername, String passwort, boolean admin) {
        this.name = name;
        this.vorname = vorname;
        this.isMan = isMan;
        this.benutzername = benutzername;
        this.passwort = passwort;
        this.admin = admin;
        System.out.println("**Neuen Benutzer mit dem Benutzernamen " + benutzername + " angelegt!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("**Name geändert");
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
        System.out.println("**Vorname geändert");
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
        System.out.println("**Geschlecht geändert");
    }

    public int getRaum() {
        return raum;
    }

    public void setRaum(int raum) {
        this.raum = raum;
        System.out.println("**Raum geändert");
    }

    public long getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(long telefonnummer) {
        this.telefonnummer = telefonnummer;
        System.out.println("**Telefonnummer geändert");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        System.out.println("**E-Mail geändert");
    }

    public String getBenutzername() {
        return benutzername;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
        System.out.println("**Benutzernamen geändert");
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
        System.out.println("**Passwort geändert");
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
        System.out.println("**Adminzugriff geändert");
    }
}