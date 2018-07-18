package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class BodenUndGebaeude extends Asset {
    private int plz;
    private String ort;
    private String strasse;
    private String hausnummer;
    private double flaeche;
    private String besitzer;

    private String[] paramNames = {
            "Postleitzahl",
            "Ort",
            "Strasse",
            "Hausnummer",
            "Flächengröße",
            "Besitzer"
    };
    private int paramAnzahl = paramNames.length;


    // Konstruktor ohne Parameter
    public BodenUndGebaeude() {
        System.out.println("[INFO] BodenUndGebaeude angelegt!");
    }

    // Konsolenausgabe aller Parameter für Testzwecke
    public void display() {
        super.display();
        System.out.println("PLZ & Ort:           " + plz + " " + ort);
        System.out.println("Straße + Hausnummer: " + strasse + " " + hausnummer);
        System.out.println("Größe in m^2:        " + flaeche);
        System.out.println("Besitzer:            " + besitzer);
    }

    // Getter und Setter
    public int getParamAnzahl(){
        return super.getParamAnzahl()+ paramAnzahl;
    }
    public String[] getParamNames(){
        String[] superiorParams = super.getParamNames();
        String[] allParams = new String[getParamAnzahl()];
        for(int i = 0; i < superiorParams.length; i++){
            allParams[i] = superiorParams[i];
        }
        int anz = 0;
        for(int i = superiorParams.length; i < allParams.length; i++){
            allParams[i] = this.paramNames[anz];
            anz++;
        }
        return allParams;
    }

    public int getPlz() {
        return plz;
    }
    public void setPlz(int plz) {
        this.plz = plz;
        System.out.println("[EDIT] Postleitzahl geändert");
    }
    public String getOrt() {
        return ort;
    }
    public void setOrt(String ort) {
        this.ort = ort;
        System.out.println("[EDIT] Ort geändert");
    }
    public String getStrasse() {
        return strasse;
    }
    public void setStrasse(String strasse) {
        this.strasse = strasse;
        System.out.println("[EDIT] Straße geändert");
    }
    public String getHausnummer() {
        return hausnummer;
    }
    public void setHausnummer(String hausnummer) {
        this.hausnummer = hausnummer;
        System.out.println("[EDIT] Hausnummer geändert");
    }
    public double getFlaeche() {
        return flaeche;
    }
    public void setFlaeche(double flaeche) {
        this.flaeche = flaeche;
        System.out.println("[EDIT] Fläche geändert");
    }
    public String getBesitzer() {
        return besitzer;
    }
    public void setBesitzer(String besitzer) {
        this.besitzer = besitzer;
        System.out.println("[EDIT] Besitzer geändert");
    }
}
