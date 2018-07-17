package Data;

/**
 *
 *
 * @author mixd
 * @version 1.0
 */

public class Hardware extends Asset {
    private int raum;

    private int paramAnzahl = 1;
    private String[] paramNames = {
            "Raum"
    };

    public Hardware() {
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
