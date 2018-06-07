package data;

import java.util.Date;

/*

    Vorläufige Klasse für ein Gegenstandseintrag im Inventar

    Muss noch um umfangreiche Funktionen Erweitert werden

 */
public class Item {
	protected static long inventarnr;
	protected static String itemtyp;
	protected static String itembez;
	protected static double preis;
	protected static int tnd;
	protected static String sg;//sachgebiet
	protected static Date insdate;//Inserierungsdatum

    public Item(long inventarnr, String itemtyp, String itembez, double preis, int tnd, String sg, Date insdate) {
        this.setInventarnr(inventarnr);
        this.setItemtyp(itemtyp);
        this.setItembez(itembez);
        this.setPreis(preis);
        this.setTnd(tnd);
        this.setSg(sg);
        this.setInsdate(insdate);
    }

	public long getInventarnr() {
		return inventarnr;
	}

	public void setInventarnr(long inventarnr) {
		this.inventarnr = inventarnr;
	}

	public String getItemtyp() {
		return itemtyp;
	}

	public void setItemtyp(String itemtyp) {
		this.itemtyp = itemtyp;
	}

	public String getItembez() {
		return itembez;
	}

	public void setItembez(String itembez) {
		this.itembez = itembez;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public int getTnd() {
		return tnd;
	}

	public void setTnd(int tnd) {
		this.tnd = tnd;
	}

	public String getSg() {
		return sg;
	}

	public void setSg(String sg) {
		this.sg = sg;
	}

	public Date getInsdate() {
		return insdate;
	}

	public void setInsdate(Date insdate) {
		this.insdate = insdate;
	}

	

   }
