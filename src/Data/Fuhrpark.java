package Data;

/**
 *
 *
 * @version 1.0
 */
public class Fuhrpark extends Asset {
	private String kennzeichen;
	private long fahrgestellnummer;
	private int kw;
	private int ps;
	private int kilometerstand;

	private String[] paramNames = {
			"Kennzeichen",
			"Fahrgestellnummer",
			"Kilometerstand",
			"Leistung"
		};
	private int paramAnzahl = paramNames.length;


	@Override
	public String concatData() {
		String concat = getBezeichnung() + " " + getAnschaffungswert() + " " + getTnd() + " " + getKennzeichen() + " " + getFahrgestellnummer() + " " + getKilometerstand() + " " + getKw() + " " + getPs();
		return concat.toLowerCase();
	}

	/**
	 * Konstruktor ohne Parameter
	 */
	public Fuhrpark() {
		System.out.println("[KONSTRUKTOR] Fuhrpark ohne Parameter angelegt!");
	}

	/**
	 * Konsolenausgabe alle Parameter für Testzwecke
	 */
	public void display() {
		super.display();
		System.out.println("Kennzeichen:         " + kennzeichen);
		System.out.println("Fahrgestellnummer:   " + fahrgestellnummer);
		System.out.println("Leistung:            " + kw + " kw (" + ps + " ps)");
		System.out.println("Kilomenterstand:     " + kilometerstand);
	}

	// Getter und Setter
	public int getParamAnzahl(){
		return super.getParamAnzahl()+ paramAnzahl;
	}
	public String[] getParamNames(){
		String[] superiorParams = super.getParamNames();

		int anz = 0;
		String[] temp = new String[superiorParams.length-1];

		for(int i = 0; i <superiorParams.length; i++){
			if(superiorParams[i].equals("Anzahl"))continue;
			temp[anz] = superiorParams[i];
			anz++;
		}
		superiorParams = temp;

		String[] allParams = new String[getParamAnzahl()-1];

		for(int i = 0; i < superiorParams.length; i++){
			allParams[i] = superiorParams[i];
		}

		anz = 0;

		for(int i = superiorParams.length; i < allParams.length; i++){
			allParams[i] = this.paramNames[anz];
			anz++;
		}
		return allParams;
	}
	public String getKennzeichen() {
		return kennzeichen;
	}
	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
		System.out.println("[EDIT] Kennzeichen geändert");
	}
	public long getFahrgestellnummer() {
		return fahrgestellnummer;
	}
	public void setFahrgestellnummer(long fahrgestellnummer) {
		this.fahrgestellnummer = fahrgestellnummer;
		System.out.println("[EDIT] Fahrgestellnummer geändert");
	}
	public int getKw() {
		return kw;
	}
	public void setKw(int kw) {
		this.kw = kw;
		this.ps = (int) round(kw*1.35962, 0);
		System.out.println("[EDIT] Leistung geändert");
	}
	public void setPs(int ps) {
		this.ps = ps;
		this.kw = (int) round(ps/1.35962, 0);
		System.out.println("[EDIT] Leistung geändert");
	}
	public int getPs() {
		return ps;
	}
	public int getKilometerstand() {
		return kilometerstand;
	}
	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
		System.out.println("[EDIT] Kilometerstand geändert");
	}
}
