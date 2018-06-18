package Data;

import java.util.Date;

public class Mobiliar extends Item{
    private int raum;

    private int ParamAnzahl = 1;
    private String[] paramNames = {
            "Raum"
    };

    public Mobiliar() {
    }

    public Mobiliar(long inventarnummer, String bezeichnung, double anschaffungswert, int tnd, Date ablaufdatum, Sachgebiet sachgebiet, Date inserierungsdatum, int anzahl, int raum) {
        super(inventarnummer, bezeichnung, anschaffungswert, tnd, ablaufdatum, sachgebiet, inserierungsdatum, anzahl);
        this.raum = raum;
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
