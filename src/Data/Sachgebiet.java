package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Sachgebiet extends Abteilung {

	private Person sgLeiter;


	// Konstruktoren
	public Sachgebiet() {
		System.out.println("[INFO] Sachgebiet ohne Parameter angelegt!");
	}
	public Sachgebiet(Person sgLeiter) {
		System.out.println("[INFO] Sachgebiet mit Parameter angelegt!");
		this.sgLeiter = sgLeiter;
	}

	// Konsolenausgabe aller Parameter für Testzwecke
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE SACHGEBIET");
		System.out.println("Sachgebietsleiter");
		System.out.println("-----------------");
		sgLeiter.display();
	}


}
