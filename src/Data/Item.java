package Data;

import java.util.Date;

/*

    Vorläufige Klasse für ein Gegenstandseintrag im Inventar

    Muss noch um umfangreiche Funktionen Erweitert werden

 */
public abstract class Item {
    private long inventarnummer;
	private String bezeichnung;
	private double anschaffungswert;
	private double buchwert;
	private int tnd;
	private Date ablaufdatum;
	private Sachgebiet sachgebiet;
	private Date inserierungsdatum;
	private int anzahl;

	public Item() {

    }

    public Item(long inventarnummer, String bezeichnung, double anschaffungswert, int tnd, Date ablaufdatum, Sachgebiet sachgebiet, Date inserierungsdatum, int anzahl) {
        this.inventarnummer = inventarnummer;
	    this.bezeichnung = bezeichnung;
        this.anschaffungswert = anschaffungswert;
        this.buchwert = anschaffungswert;
        this.tnd = tnd;
        this.ablaufdatum = ablaufdatum;
        this.sachgebiet = sachgebiet;
        this.inserierungsdatum = inserierungsdatum;
        this.anzahl = anzahl;
    }

    public void display() {
	    System.out.println("Inventarnummer: " + inventarnummer);
        System.out.println("Bezeichnung: " + bezeichnung);
        System.out.println("Anschaffungswert: " + anschaffungswert);
        System.out.println("Buchwert: " + buchwert);
        System.out.println("Techn. Nutzungsdauer: " + tnd);
        System.out.println("Ablaufdatum: " + ablaufdatum);
        //System.out.println("Inventarnummer: " + sachgebiet);
        System.out.println("Inserierungsdatum: " + inserierungsdatum);
        System.out.println("Anzahl: " + anzahl);
    }

    public Item(long inventarnummer) {
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

    public Date getInserierungsdatum() {
        return inserierungsdatum;
    }

    public void setInserierungsdatum(Date inserierungsdatum) {
        this.inserierungsdatum = inserierungsdatum;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
