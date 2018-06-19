package Data;

public class Fuhrpark extends Asset {
	private String kennzeichen;
	private long fahrgestellnummer;
	private int kw;
	private int ps;
	private int kilometerstand;

	private int paramAnzahl = 4;
	private String[] paramNames = {
			"Kennzeichen",
			"Fahrgestellnummer",
			"Kilometerstand",
			"Leistung"
		};

	public Fuhrpark() {

	}

	public int getParamAnzahl(){
		return super.getParamAnzahl()+ paramAnzahl;
	}

	public String[] getParamNames(){
		String[] superiorParams = super.getParamNames();
		String[] allParams = new String[getParamAnzahl()];

		for(int i = 0; i < superiorParams.length; i++){
			allParams[i] = superiorParams[i];
		}
		int anz = 0;
		for(int i = superiorParams.length; i < allParams.length; i++){
			allParams[i] = this.paramNames[anz];
			anz++;
		}
		return allParams;
	}

	public void display() {
		super.display();
		System.out.println("Kennzeichen: " + kennzeichen);
		System.out.println("Fahrgestellnummer: " + fahrgestellnummer);
		System.out.println("Leistung: " + kw + "(" + ps + ")");
		System.out.println("Kilomenterstand: " + kilometerstand);
	}

	public String getKennzeichen() {
		return kennzeichen;
	}

	public void setKennzeichen(String kennzeichen) {
		this.kennzeichen = kennzeichen;
	}

	public long getFahrgestellnummer() {
		return fahrgestellnummer;
	}

	public void setFahrgestellnummer(long fahrgestellnummer) {
		this.fahrgestellnummer = fahrgestellnummer;
	}

	public int getKw() {
		return kw;
	}

	public void setKw(int kw) {
		this.kw = kw;
		this.ps = (int) round(kw*1.35962, 0);
	}

	public void setPs(int ps) {
		this.ps = ps;
		this.kw = (int) round(ps/1.35962, 0);
	}

	public int getPs() {
		return ps;
	}

	public int getKilometerstand() {
		return kilometerstand;
	}

	public void setKilometerstand(int kilometerstand) {
		this.kilometerstand = kilometerstand;
	}
}
