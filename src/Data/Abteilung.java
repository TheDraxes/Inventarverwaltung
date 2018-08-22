package Data;

import java.util.ArrayList;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Abteilung extends Organisation{

	private ArrayList<Sachgebiet> sachgebiete = new ArrayList<>();

	// Konstruktoren
	public Abteilung() {
		System.out.println("[KONSTRUKTOR] Abteilung ohne Parameter angelegt!");
	}
	public Abteilung(Person leiter, String name, String kuerzel) {
		System.out.println("[KONSTRUKTOR] Abteilung mit Parameter angelegt!");
		this.leiter = leiter;
		this.name = name;
		this.kuerzel = kuerzel;
	}

	public ArrayList<Sachgebiet> getSachgebiete() {
		return sachgebiete;
	}

	public boolean sachgebietExisting(){
		if(sachgebiete.size() > 0) {
			return true;
		}
		return false;
	}

	// Konsolenausgabe aller Parameter für Testzwecke
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
	public Person getLeiter() {
		return leiter;
	}
	public void setLeiter(Person leiter) {
		this.leiter = leiter;
		System.out.println("[EDIT] Abteilungsleiter geändert");
	}
}
