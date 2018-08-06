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
	public Abteilung(Person leiter, String name, String kürzel) {
		System.out.println("[KONSTRUKTOR] Abteilung mit Parameter angelegt!");
		this.leiter = leiter;
		this.name = name;
		this.kürzel = kürzel;
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
		System.out.println("Abteilungsleiter");
		System.out.println("----------------");
		System.out.println("Leiter: " + leiter.getUsername());
		System.out.println("Name:   " + name);
		System.out.println("Kürzel: " + kürzel);
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
