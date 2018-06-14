package Data;

import java.util.Date;

public class Fuhrpark extends Item{
	private String kennzeichen;
	private String fahrzeugart;
	private long fahrgestellnummer;
	private double kw;
	private double ps;
	private int kilometerstand;

	public Fuhrpark() {
		super();
	}

	public Fuhrpark(long inventarnummer, String bezeichnung, double anschaffungswert, int tnd, Date ablaufdatum, Sachgebiet sachgebiet, Date inserierungsdatum, int anzahl, String kennzeichen, String fahrzeugart, long fahrgestellnummer, double kw, int kilometerstand) {
		super(inventarnummer, bezeichnung, anschaffungswert, tnd, ablaufdatum, sachgebiet, inserierungsdatum, anzahl);
		this.kennzeichen = kennzeichen;
		this.fahrzeugart = fahrzeugart;
		this.fahrgestellnummer = fahrgestellnummer;
		this.kw = kw;
		this.ps = kw*1.35962;
		this.kilometerstand = kilometerstand;
	}

	public void display() {
		super.display();
		System.out.println("Kennzeichen: " + kennzeichen);
		System.out.println("Fahrzeugart: " + fahrzeugart);
		System.out.println("Fahrgestellnummer: " + fahrgestellnummer);
		System.out.println("Leistung: " + kw + "(" + ps + ")");
		System.out.println("Kilomenterstand: " + kilometerstand);
	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public String getFahrzeugart() {
		return fahrzeugart;
	}

	public void setFahrzeugart(String fahrzeugart) {
		this.fahrzeugart = fahrzeugart;
	}

	public long getFahrgestellnummer() {
		return fahrgestellnummer;
	}

	public void setFahrgestellnummer(long fahrgestellnummer) {
		this.fahrgestellnummer = fahrgestellnummer;
	}

	public double getKw() {
		return kw;
	}

	public void setKw(double kw) {
		this.kw = kw;
		this.ps = kw*1.35962;
	}

	public double getPs() {
		return ps;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
	}
}
