package Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */
public abstract class Asset implements Serializable {
    private long inventarnummer;
	private String bezeichnung;
	private double anschaffungswert;
	private String anschaffungswertString;
	private double buchwert;
	private int tnd;
	private Date ablaufdatum;
	private Sachgebiet standort;
	private Date inserierungsdatum;
	private String inserierungsdatumString;
	private int anzahl;

	private String[] paramNames = {
            "Bezeichnung",
            "Anschaffungswert",
            "Tnd",
            "Anzahl"
	};
    private int paramAnzahl = paramNames.length;
    private final int ParamAnzahl = paramNames.length;


	// Konstruktor zum Anlegen eines Assets ohne Parameter
	public Asset() {
        System.out.println("[KONSTRUKTOR] Asset ohne Parameter angelegt!");
    }

    // Konstruktor zum Anlegen eines Assets ohne Parameter nur mit Inventarnummer
    public Asset(long inventarnummer) {
        this.inventarnummer = inventarnummer;
        System.out.println("[KONSTRUKTOR] Asset mit Inventarnummer angelegt!");
    }

    // Konstruktor zum Anlegen eines Assets mit Parametern
    public Asset(long inventarnummer, String bezeichnung, double anschaffungswert, int tnd, Date ablaufdatum, Sachgebiet standort, Date inserierungsdatum, int anzahl) {
        this.inventarnummer = inventarnummer;
	    this.bezeichnung = bezeichnung;
        this.setAnschaffungswert(anschaffungswert);
        this.buchwert = anschaffungswert;
        this.tnd = tnd;
        this.ablaufdatum = ablaufdatum;
        this.standort = standort;
        this.anzahl = anzahl;
        this.setInserierungsdatum(inserierungsdatum);
        System.out.println("[KONSTRUKTOR] Asset mit Parametern angelegt!");
    }

    // Methode zum Runden auf x Stellen
    public static double round(double value, int x) {
        System.out.println("[INFO] round aufgerufen");
        if (x < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(x, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    // Konsolenausgabe aller Parameter für Testzwecke
    public void display() {
        System.out.println("[INFO] DISPLAYMETHODE EINES ASSETS AUFGERUFEN");
        System.out.println("=============================================");
	    System.out.println("Inventarnummer:      " + inventarnummer);
        System.out.println("Bezeichnung:         " + bezeichnung);
        System.out.println("Anschaffungswert:    " + anschaffungswert);
        System.out.println("Buchwert:            " + buchwert);
        System.out.println("Techn. Nutzungsdauer:" + tnd);
        System.out.println("Ablaufdatum:         " + ablaufdatum);
        //System.out.println("Sachgebiet:          " + standort);
        System.out.println("Inserierungsdatum:   " + inserierungsdatum);
        System.out.println("Anzahl:              " + anzahl);
    }

    // Getter & Setter
    public int getParamAnzahl(){
        return this.paramAnzahl;
    }
    public String[] getParamNames(){
        return this.paramNames;
    }

    public long getInventarnummer() {
        return inventarnummer;
    }
    public void setInventarnummer(long inventarnummer) {
        this.inventarnummer = inventarnummer;
        System.out.println("[EDIT] Inventarnummer geändert");
    }
    public String getBezeichnung() {
        return bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
        System.out.println("[EDIT] Bezeichnung geändert");
    }
    public double getAnschaffungswert() {
        return anschaffungswert;
    }
    public void setAnschaffungswert(double anschaffungswert) {
        this.anschaffungswert = anschaffungswert;
        this.anschaffungswertString = round(anschaffungswert,2) + "€";
        System.out.println("[EDIT] Anschaffungswert geändert");
    }
    public double getBuchwert() {
        return buchwert;
    }
    public void setBuchwert(double buchwert) {
        this.buchwert = buchwert;
        System.out.println("[EDIT] Buchwert geändert");
    }
    public int getTnd() {
        return tnd;
    }
    public void setTnd(int tnd) {
        this.tnd = tnd;
        System.out.println("[EDIT] TND geändert");
    }
    public Date getAblaufdatum() {
        return ablaufdatum;
    }
    public void setAblaufdatum(Date ablaufdatum) {
        this.ablaufdatum = ablaufdatum;
        System.out.println("[EDIT] Ablaufdatum geändert");
    }
    public Sachgebiet getStandort() {
        return standort;
    }
    public void setStandort(Sachgebiet standort) {
        this.standort = standort;
        System.out.println("[EDIT] Standort (Sachgebiet) geändert");
    }
    public String getAnschaffungswertString() {
        return anschaffungswertString;
    }
    public Date getInserierungsdatum() {
        return inserierungsdatum;
    }
    public void setInserierungsdatum(Date inserierungsdatum) {
        this.inserierungsdatum = inserierungsdatum;
        SimpleDateFormat df = new SimpleDateFormat( "dd-MM-yyyy" );
        this.inserierungsdatumString = ""+ df.format(inserierungsdatum);
        System.out.println("[EDIT] Inserierungsdatum geändert");
    }
    public String getInserierungsdatumString() {
        return inserierungsdatumString;
    }
    public void setInserierungsdatumString(String inserierungsdatumString) {
        this.inserierungsdatumString = inserierungsdatumString;
        System.out.println("[EDIT] InserierungsdatumString geändert");
    }
    public int getAnzahl() {
        return anzahl;
    }
    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
        System.out.println("[EDIT] Anzahl geändert");
    }
}
