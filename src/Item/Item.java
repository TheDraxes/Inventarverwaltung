package Item;

import java.util.Date;

/*

    Vorläufige Klasse für ein Gegenstandseintrag im Inventar

    Muss noch um umfangreiche Funktionen Erweitert werden

 */
public abstract class Item {
	protected long inventarnr;
	protected String itemtyp;
	protected String itembez;
	protected double preis;
	protected int tnd;
	protected String sg;//sachgebiet
	protected Date insdate;//Inserierungsdatum

    public Item(){}

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

	public String getClassString(){
    	String a = this.getClass().toString();
    	System.out.println(a.lastIndexOf("."));
    	return a.substring(a.lastIndexOf(".")+1, a.length());
	}
   }
