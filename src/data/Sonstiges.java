package data;

public class Sonstiges extends Item{

	private String beschreibung;

	public Sonstiges(String beschreibung) {
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
