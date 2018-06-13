package Item;

import java.util.Date;

public class Mobiliar extends Item{
	private String raum;

	
	public Mobiliar(long inventarnr, String itemtyp, String itembez, double preis, int tnd, String sg, Date insdate,String raum) {
		super(inventarnr, itemtyp, itembez,preis,tnd,sg,insdate);
		this.raum = raum;
		
	}
	public Mobiliar(){}
	
	public String getRaum() {
		return raum;
	}

	public void setRaum(String raum) {
		this.raum = raum;
	}
	
	

}
