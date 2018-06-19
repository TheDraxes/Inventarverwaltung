package Data;

public class BodenUndGebaeude extends Asset {
    private int plz;
    private String ort;
    private String strasse;
    private String hausnummer;
    private double flaeche;
    private String besitzer;

    private int paramAnzahl = 6;
    private String[] paramNames = {
            "Postleitzahl",
            "Ort",
            "Strasse",
            "Hausnummer",
            "Flächengröße",
            "Besitzer"
    };

    public BodenUndGebaeude() {
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


}
