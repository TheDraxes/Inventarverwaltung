package data;

public class Software extends Item{
	public double version;

	
	public Software(double version) {
		super(inventarnr, itemtyp, itembez,preis,tnd,sg,insdate);
		this.version = version;
	}
	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}
	
	
}
