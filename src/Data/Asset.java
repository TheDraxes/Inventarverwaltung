package Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/*

    Vorläufige Klasse für ein Gegenstandseintrag im Inventar

    Muss noch um umfangreiche Funktionen Erweitert werden

 */
public abstract class Asset implements Serializable {
    private long inventarnummer;
	private String bezeichnung;
	private double anschaffungswert;
	private String anschaffungswertString;
	private double buchwert;
	private int tnd;
	private Date ablaufdatum;
	private Sachgebiet sachgebiet;
	private Date inserierungsdatum;
	private String inserierungsdatumString;
	private int anzahl;

	private int paramAnzahl = 4;

	//Namen der Superioren Parameter
	private String[] paramNames = {
            "Bezeichnung",
            "Anschaffungswert",
            "Tnd",
            "Anzahl"
	};

	public Asset() {


    }

    public Asset(long inventarnummer, String bezeichnung, double anschaffungswert, int tnd, Date ablaufdatum, Sachgebiet sachgebiet, Date inserierungsdatum, int anzahl) {
        this.inventarnummer = inventarnummer;
	    this.bezeichnung = bezeichnung;
        this.setAnschaffungswert(anschaffungswert);
        this.buchwert = anschaffungswert;
        this.tnd = tnd;
        this.ablaufdatum = ablaufdatum;
        this.sachgebiet = sachgebiet;
        this.anzahl = anzahl;
        this.setInserierungsdatum(inserierungsdatum);
    }

    public int getParamAnzahl(){
	    return this.paramAnzahl;
    }

    public String[] getParamNames(){
	    return this.paramNames;
    }

    public void display() {
	    System.out.println("Inventarnummer:      " + inventarnummer);
        System.out.println("Bezeichnung:         " + bezeichnung);
        System.out.println("Anschaffungswert:    " + anschaffungswert);
        System.out.println("Buchwert:            " + buchwert);
        System.out.println("Techn. Nutzungsdauer:" + tnd);
        System.out.println("Ablaufdatum:         " + ablaufdatum);
        //System.out.println("Inventarnummer: " + sachgebiet);
        System.out.println("Inserierungsdatum:   " + inserierungsdatum);
        System.out.println("Anzahl:              " + anzahl);
    }

    public Asset(long inventarnummer) {
        this.inventarnummer = inventarnummer;
    }

    public long getInventarnummer() {
        return inventarnummer;
    }

    public void setInventarnummer(long inventarnummer) {
        this.inventarnummer = inventarnummer;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public double getAnschaffungswert() {
        return anschaffungswert;
    }

    public void setAnschaffungswert(double anschaffungswert) {
        this.anschaffungswert = anschaffungswert;
        this.anschaffungswertString = round(anschaffungswert,2) + "€";
    }

    public double getBuchwert() {
        return buchwert;
    }

    public void setBuchwert(double buchwert) {
        this.buchwert = buchwert;
    }

    public int getTnd() {
        return tnd;
    }

    public void setTnd(int tnd) {
        this.tnd = tnd;
    }

    public Date getAblaufdatum() {
        return ablaufdatum;
    }

    public void setAblaufdatum(Date ablaufdatum) {
        this.ablaufdatum = ablaufdatum;
    }

    public Sachgebiet getSachgebiet() {
        return sachgebiet;
    }

    public void setSachgebiet(Sachgebiet sachgebiet) {
        this.sachgebiet = sachgebiet;
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
    }

    public String getInserierungsdatumString() {
        return inserierungsdatumString;
    }

    public void setInserierungsdatumString(String inserierungsdatumString) {
        this.inserierungsdatumString = inserierungsdatumString;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
