package Data;

import java.util.ArrayList;

/**
 *
 *
 * @version 1.0
 */
public class Abteilung extends Organisation{
	private ArrayList<Sachgebiet> sachgebiete = new ArrayList<>();

	/**
	 * Konstruktor zum Anlegen einer neuen Abteilung ohne Parameter
	 */
	public Abteilung() {
		System.out.println("[KONSTRUKTOR] Abteilung ohne Parameter angelegt!");
	}

	/**
	 * Konstruktor zum Anlegen einer neuen Abteilung mit Parameter
	 */
	public Abteilung(Person leiter, String name, String kuerzel) {
		System.out.println("[KONSTRUKTOR] Abteilung mit Parameter angelegt!");
		this.leiter = leiter;
		this.name = name;
		this.kuerzel = kuerzel;
	}

	/**
	 * sachgebietExisting prüft ob der Abteilung mind. 1 Sachgebiet zugewiesen wurde
	 *
	 * @author mixd
	 */
	public boolean sachgebietExisting(){
		if(sachgebiete.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Konsolenausgabe einer Abteilung (mit allen Parametern) für Testzwecke
	 */
	public void display() {
		System.out.println("[INFO] DISPLAYMETHODE ABTEILUNG");
		/*System.out.println("Abteilungsleiter");
		System.out.println("----------------");*/
		System.out.println("Leiter: " + leiter.getUsername());
		System.out.println("Name:   " + name);
		System.out.println("Kürzel: " + kuerzel);
		System.out.println("[INFO] SACHGEBIETE IN DER ABTEILUNG");
		System.out.println("===================================");
		if(sachgebiete.size() > 0) {
			for (Sachgebiet s: sachgebiete) {
				s.display();
			}
		} else
			System.out.println("Keine Sachgebiete eingetragen!");
		System.out.println("-----------------------------------------------------------------------------");
	}

	// Getter und Setter
	public ArrayList<Sachgebiet> getSachgebiete() {
		return sachgebiete;
	}
	public Person getLeiter() {
		return leiter;
	}
	public void setLeiter(Person leiter) {
		this.leiter = leiter;
		System.out.println("[EDIT] Abteilungsleiter geändert");
	}
}
