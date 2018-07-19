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
		System.out.println("[KONSTRUKTOR] Abteilung ohne Parameter angelegt!");
	}
	public Abteilung(Person abteilungsleiter) {
		System.out.println("[KONSTRUKTOR] Abteilung mit Parameter angelegt!");
		this.abteilungsleiter = abteilungsleiter;
	}

	// Konsolenausgabe aller Parameter für Testzwecke
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE ABTEILUNG");
		System.out.println("Abteilungsleiter");
		System.out.println("----------------");
		abteilungsleiter.display();
	}

	// Getter und Setter
	public Person getAbteilungsleiter() {
		return abteilungsleiter;
	}
	public void setAbteilungsleiter(Person abteilungsleiter) {
		this.abteilungsleiter = abteilungsleiter;
		System.out.println("[EDIT] Abteilungsleiter geändert");
	}
}
