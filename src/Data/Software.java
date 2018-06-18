package Data;

import java.util.Date;

public class Software extends Item{
    private String version;

    private int ParamAnzahl = 1;
    private String[] paramNames = {
            "Softwareversion"
    };

    public Software() {
    }

    public Software(long inventarnummer, String bezeichnung, double anschaffungswert, int tnd, Date ablaufdatum, Sachgebiet sachgebiet, Date inserierungsdatum, int anzahl, String version) {
        super(inventarnummer, bezeichnung, anschaffungswert, tnd, ablaufdatum, sachgebiet, inserierungsdatum, anzahl);
        this.version = version;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
