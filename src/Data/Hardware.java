package Data;

/**
 *
 *
 * @version 1.0
 */
public class Hardware extends Asset {
    private int raum;

    private String[] paramNames = {
            "Raum"
    };
    private int paramAnzahl = paramNames.length;

    @Override
    public String concatData() {
        String concat = getBezeichnung() + " " + getAnschaffungswert() + " " + getTnd() + " " + getRaum();
        return concat.toLowerCase();
    }

    /**
     * Konstruktor ohne Parameter
     */
    public Hardware() {
        System.out.println("[KONSTRUKTOR] Hardware ohne Parameter angelegt!");
    }

    /**
     * Konsolenausgabe alle Parameter für Testzwecke
     */
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
