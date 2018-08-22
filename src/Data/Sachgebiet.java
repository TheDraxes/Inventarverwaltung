package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Sachgebiet extends Organisation{

  private String abteilung;

	// Konstruktoren
	public Sachgebiet() {
		System.out.println("[KONSTRUKTOR] Sachgebiet ohne Parameter angelegt!");
	}
	public Sachgebiet(Person sachgebietsleiter,String name,String kuerzel, Abteilung abteilung) {
		super(sachgebietsleiter,name,kuerzel);
		System.out.println("[KONSTRUKTOR] Sachgebiet mit Parameter angelegt!");
	}

	// Konsolenausgabe aller Parameter für Testzwecke
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE SACHGEBIET");
		/*System.out.println("Sachgebietsleiter");
		System.out.println("-----------------");
		this.leiter.display();*/
		System.out.println("Abteilung: " + this.abteilung);
		System.out.println("Kürzel   : " + this.kuerzel);
	}
	
	public String getAbtKuerzel(){
      return this.abteilung;
	}

	public void setAbtKuerzel(String abteilung){
	  this.abteilung = abteilung;
	}
}
