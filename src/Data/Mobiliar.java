package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Mobiliar extends Asset {
    private int raum;

    private String[] paramNames = {
            "Raum"
    };
    private int paramAnzahl = paramNames.length;

    // Konstruktor ohne Parameter
    public Mobiliar() {
        System.out.println("[INFO] Mobiliar ohne Parameter angelegt!");
    }

    // Konsolenausgabe aller Parameter für Testzwecke
    public void display() {
        super.display();
        System.out.println("Raum:                " + raum);
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

    public int getRaum() {
        return raum;
    }
    public void setRaum(int raum) {
        this.raum = raum;
        System.out.println("[EDIT] Raum geändert");
    }
}
