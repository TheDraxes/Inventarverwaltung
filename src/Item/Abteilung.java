package Item;

public class Abteilung {
	private String abtleiter;
	private String raum;
	private long telnr;
	private String mail;
	private int anz;
	
	
	public Abteilung (String abtleiter,String raum, long telnr, String mail, int anz) {
		this.abtleiter = abtleiter;
		this.raum = raum;
		this.telnr = telnr;
		this.mail = mail;
		this.setAnz(anz);
		
	}

	public String getAbtleiter() {
		return abtleiter;
	}

	public void setAbtleiter(String abtleiter) {
		this.abtleiter = abtleiter;
	}

	public String getRaum() {
		return raum;
	}

	public void setRaum(String raum) {
		this.raum = raum;
	}

	public long getTelnr() {
		return telnr;
	}

	public void setTelnr(long telnr) {
		this.telnr = telnr;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getAnz() {
		return anz;
	}

	public void setAnz(int anz) {
		this.anz = anz + Sachgebiet.getSgAnz();
	}

}
