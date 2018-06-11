package data;

import java.util.Date;

public class Sonstiges extends Item{

	private String beschreibung;

	public Sonstiges(long inventarnr, String itemtyp, String itembez, double preis, int tnd, String sg, Date insdate,String beschreibung) {
		super(inventarnr, itemtyp, itembez,preis,tnd,sg,insdate);
		this.beschreibung = beschreibung;
		
	}
	
	
	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	
	
}
