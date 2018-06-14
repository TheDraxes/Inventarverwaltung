package Data;

public class Sachgebiet extends Abteilung {

	private String sgLeiter;
	private String sgRaum;
	private long sgTelnr;
	private String sgMail;
	private static int sgAnz;
	
	public Sachgebiet(String abtleiter,String raum, long telnr, String mail, int anz, String sgLeiter, String sgRaum,long sgTelnr, String sgMail, int sgAnz) {
	super(abtleiter, raum, telnr, mail, anz);
	this.sgLeiter = sgLeiter;
	this.sgRaum = sgRaum;
	this.sgTelnr = sgTelnr;
	this.sgMail = sgMail;
	this.sgAnz = sgAnz;
	}

	
	
	
	
	public String getSgLeiter() {
		return sgLeiter;
	}

	public void setSgLeiter(String sgLeiter) {
		this.sgLeiter = sgLeiter;
	}

	public String getSgRaum() {
		return sgRaum;
	}

	public void setSgRaum(String sgRaum) {
		this.sgRaum = sgRaum;
	}

	public long getSgTelnr() {
		return sgTelnr;
	}

	public void setSgTelnr(long sgTelnr) {
		this.sgTelnr = sgTelnr;
	}

	public String getSgMail() {
		return sgMail;
	}

	public void setSgMail(String sgMail) {
		this.sgMail = sgMail;
	}

	public static int getSgAnz() {
		return sgAnz;
	}

	public void setSgAnz(int sgAnz) {
		this.sgAnz = sgAnz;
	}
	
	
	
}
