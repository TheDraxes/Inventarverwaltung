package Data;

/**
 *
 *
 * @author mixd
 *
 * @version 1.0
 */

public class Abteilung {
	private Person abteilungsleiter;

	// Konstruktoren
	public Abteilung() {
		System.out.println("[INFO] Abteilung ohne Parameter angelegt!");
	}
	public Abteilung(Person abteilungsleiter) {
		System.out.println("[INFO] Abteilung mit Parameter angelegt!");
		this.abteilungsleiter = abteilungsleiter;
	}

	// Konsolenausgabe aller Parameter für Testzwecke
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE ABTEILUNG");
		System.out.println("Abteilungsleiter");
		System.out.println("----------------");
		abteilungsleiter.display();
	}
}
