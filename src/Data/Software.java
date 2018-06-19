package Data;

import java.util.Date;

public class Software extends Item{
    private String version;

    private int paramAnzahl = 1;
    private String[] paramNames = {
            "Softwareversion"
    };

    public Software() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
