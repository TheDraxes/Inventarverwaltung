package data;

import java.util.Date;

public class Fuhrpark extends Item{
	private String kz;
	private String autoart;
	private long fgnr;
	private int power;
	private int kmStand;
	
	public Fuhrpark(long inventarnr, String itemtyp, String itembez, double preis, int tnd, String sg, Date insdate,String kz, String autoart, long fgnr, int power, int kmStand) {
		super(inventarnr, itemtyp, itembez,preis,tnd,sg,insdate);
		this.kz = kz;
		this.autoart = autoart;
		this.fgnr = fgnr;
		this.power = power;
		this.kmStand = kmStand;
	}
	public Fuhrpark(){}
	
	public String getKz() {
		return kz;
	}
	public void setKz(String kz) {
		this.kz = kz;
	}
	public String getAutoart() {
		return autoart;
	}
	public void setAutoart(String autoart) {
		this.autoart = autoart;
	}
	public long getFgnr() {
		return fgnr;
	}
	public void setFgnr(long fgnr) {
		this.fgnr = fgnr;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getKmStand() {
		return kmStand;
	}
	public void setKmStand(int kmStand) {
		this.kmStand = kmStand;
	}
	
	
}
