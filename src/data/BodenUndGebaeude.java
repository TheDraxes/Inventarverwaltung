package data;

public class BodenUndGebaeude extends Item {

	private int plz;
	private String ort;
	private String anschrift;
	private int flaeche;
	private String besitzer;

	public BodenUndGebaeude(int plz, String ort, String anschrift, int flaeche, String besitzer) {
		super(inventarnr, itemtyp, itembez,preis,tnd,sg,insdate);
		this.plz = plz;
		this.ort = ort;
		this.anschrift = anschrift;
		this.flaeche = flaeche;
		this.besitzer = besitzer;
		
		
	}
	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getAnschrift() {
		return anschrift;
	}

	public void setAnschrift(String anschrift) {
		this.anschrift = anschrift;
	}

	public int getFlaeche() {
		return flaeche;
	}

	public void setFlaeche(int flaeche) {
		this.flaeche = flaeche;
	}

	public String getBesitzer() {
		return besitzer;
	}

	public void setBesitzer(String besitzer) {
		this.besitzer = besitzer;
	}

}
