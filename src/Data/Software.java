package Data;

/**
 *
 *
 * @author mixd
 *
 * @version 1.0
 */

public class Software extends Asset {
    private String version;

    private String[] paramNames = {
            "Softwareversion"
    };
    private int paramAnzahl = paramNames.length;


    // Konstruktor ohne Parameter
    public Software() {

    }

    // Konsolenausgabe aller Parameter für Testzwecke
    public void display() {
        super.display();
        System.out.println("Version:             " + version);
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

    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
        System.out.println("[EDIT] Version geändert");
    }
}
