package data;

public class Mobiliar extends Item{
	private String raum;

	
	public Mobiliar(String raum) {
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
