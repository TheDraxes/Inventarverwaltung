package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Sachgebiet extends Organisation{

	private Abteilung abteilung;

	// Konstruktoren
	public Sachgebiet() {
		System.out.println("[KONSTRUKTOR] Sachgebiet ohne Parameter angelegt!");
	}
	public Sachgebiet(Person sachgebietsleiter,String name,String kürzel, Abteilung abteilung) {
		super(sachgebietsleiter,name,kürzel);
		System.out.println("[KONSTRUKTOR] Sachgebiet mit Parameter angelegt!");
		this.abteilung = abteilung;

	}

	// Konsolenausgabe aller Parameter für Testzwecke
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE SACHGEBIET");
		System.out.println("Sachgebietsleiter");
		System.out.println("-----------------");
		this.leiter.display();
	}

	// Getter und Setter
	public Person getSachgebietsleiter() {
		return leiter;
	}
	public void setSachgebietsleiter(Person sachgebietsleiter) {
		this.leiter = sachgebietsleiter;
		System.out.println("[EDIT] Sachgebietsleiter geändert");
	}


}
