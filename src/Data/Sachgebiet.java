package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Sachgebiet extends Abteilung {

	private Person sachgebietsleiter;


	// Konstruktoren
	public Sachgebiet() {
		System.out.println("[INFO] Sachgebiet ohne Parameter angelegt!");
	}
	public Sachgebiet(Person sachgebietsleiter) {
		System.out.println("[INFO] Sachgebiet mit Parameter angelegt!");
		this.sachgebietsleiter = sachgebietsleiter;
	}

	// Konsolenausgabe aller Parameter für Testzwecke
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE SACHGEBIET");
		System.out.println("Sachgebietsleiter");
		System.out.println("-----------------");
		sachgebietsleiter.display();
	}

	// Getter und Setter
	public Person getSachgebietsleiter() {
		return sachgebietsleiter;
	}
	public void setSachgebietsleiter(Person sachgebietsleiter) {
		this.sachgebietsleiter = sachgebietsleiter;
		System.out.println("[EDIT] Sachgebietsleiter geändert");
	}
}
