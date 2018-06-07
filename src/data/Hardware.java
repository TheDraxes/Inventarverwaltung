package data;

import java.util.Date;

public class Hardware extends Item{
	private String raum;

	public Hardware(long inventarnr, String itemtyp, String itembez, double preis, int tnd, String sg, Date insdate,String raum) {
		super(inventarnr, itemtyp, itembez,preis,tnd,sg,insdate);
		this.raum = raum;
	}
	
	public String getRaum() {
		return raum;
	}

	public void setRaum(String raum) {
		this.raum = raum;
	}
	
	
}
