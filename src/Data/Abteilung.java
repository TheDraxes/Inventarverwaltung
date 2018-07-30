package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Abteilung extends Organisation{
	// Konstruktoren
	public Abteilung() {
		System.out.println("[KONSTRUKTOR] Abteilung ohne Parameter angelegt!");
	}
	public Abteilung(Person leiter, String name, String kürzel) {
		System.out.println("[KONSTRUKTOR] Abteilung mit Parameter angelegt!");
		this.leiter = leiter;
		this.name = name;
		this.kürzel = kürzel;
	}

	// Konsolenausgabe aller Parameter für Testzwecke
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE ABTEILUNG");
		System.out.println("Abteilungsleiter");
		System.out.println("----------------");
		leiter.display();
	}

	// Getter und Setter
	public Person getLeiter() {
		return leiter;
	}
	public void setLeiter(Person leiter) {
		this.leiter = leiter;
		System.out.println("[EDIT] Abteilungsleiter geändert");
	}
}
