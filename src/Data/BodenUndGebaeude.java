package Data;

import java.util.Date;

public class BodenUndGebaeude extends Item {
    private int plz;
    private String ort;
    private String strasse;
    private String hausnummer;
    private double flaeche;
    private String besitzer;

    private int ParamAnzahl = 6;
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

    public BodenUndGebaeude(long inventarnummer, String bezeichnung, double anschaffungswert, int tnd, Date ablaufdatum, Sachgebiet sachgebiet, Date inserierungsdatum, int anzahl, int plz, String ort, String strasse, String hausnummer, double flaeche, String besitzer) {
        super(inventarnummer, bezeichnung, anschaffungswert, tnd, ablaufdatum, sachgebiet, inserierungsdatum, anzahl);
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.flaeche = flaeche;
        this.besitzer = besitzer;
    }

    public int getParamAnzahl(){
        return super.getParamAnzahl()+ParamAnzahl;
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
