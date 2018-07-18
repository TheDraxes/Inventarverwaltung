package Data;

/**
 *
 *
 * @author mixd
 *
 * @version 1.0
 */

public class Sonstiges extends Asset {
    private String beschreibung;

    private String[] paramNames = {
            ""
    };
    private int paramAnzahl = paramNames.length;


    // Konstruktor ohne Parameter
    public Sonstiges() {

    }

    // Konsolenausgabe aller Parameter für Testzwecke
    public void display() {
        super.display();
        System.out.println("Beschreibung:        " + beschreibung);
    }

    // Getter und Setter
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

    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
        System.out.println("[EDIT] Beschreibung geändert");
    }
}
