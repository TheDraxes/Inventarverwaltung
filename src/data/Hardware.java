package data;

public class Hardware extends Item{
	private String raum;

	public Hardware(String raum) {
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
