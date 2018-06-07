package data;

import java.util.Date;

public class Software extends Item{
	public double version;

	
	public Software(long inventarnr, String itemtyp, String itembez, double preis, int tnd, String sg, Date insdate,double version) {
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
