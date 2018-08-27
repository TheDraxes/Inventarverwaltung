package GUI.ViewGUI.Summary;


import Data.*;
import Verwaltung.AssetContainer;
import Verwaltung.OrganisationContainer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller für das Fenster für die Kapitalzusammenfassung
 *
 *
 * @version 1.0
 */
public class SummaryController implements Initializable{
    //Label für die Anazahl an Fuhrpark
    @FXML
    private Label countFuhrpark = new Label();
    //Label für den Anlagewert an Fuhrpark
    @FXML
    private Label valueFuhrpark;

    //Label für die Anazahl an Boden und Gebäude
    @FXML
    private Label countBoden;
    //Label für den Anlagewert an Boden und Gebäude
    @FXML
    private Label valueBoden;

    //Label für die Anazahl an Software
    @FXML
    private Label countSoftware;
    //Label für den Anlagewert an Software
    @FXML
    private Label valueSoftware;

    //Label für die Anazahl an Hardware
    @FXML
    private Label countHardware;
    //Label für den Anlagewert an Hardware
    @FXML
    private Label valueHardware;

    //Label für die Anazahl an Mobiliar
    @FXML
    private Label countMobiliar;
    //Label für den Anlagewert an Mobiliar
    @FXML
    private Label valueMobiliar;

    //Label für die Anazahl an Sonstiges
    @FXML
    private Label countSonstiges;
    //Label für den Anlagewert an Sonstiges
    @FXML
    private Label valueSonstiges;

    //Label für die Summe an Anlagegenstände der Firma
    @FXML
    private Label firmSum;
    //Label für die Summe an Anlagegenstände der Abteilung
    @FXML
    private Label abtSum;

    //ComboBox mit den Abteilungen
    @FXML
    private ComboBox<String> abteilungComboBox;

    //Label für den Namen der Abteilung
    @FXML
    private Label abtName;

    //Container für die Organisationsstruktur
    private OrganisationContainer orgContainer;

    //Pfad zu dem Speicherort der Inventare
    private String path;

    //Formatter für Zahlen
    private final java.text.DecimalFormatSymbols germany
            = new java.text.DecimalFormatSymbols( new java.util.Locale( "de", "DE" ));
    private final java.text.DecimalFormat german
            = new java.text.DecimalFormat( "##,###.00", germany );


    /**
     * Initalisiert das Summary Fenster
     *
     * @see this#getSummaryOf()
     */

    @FXML
    public void initialize(){
        orgContainer = new OrganisationContainer().loadOrganisationsData();

        abteilungComboBox.setValue("- kein Eintrag gewählt -");
        abteilungComboBox.setItems(FXCollections.observableArrayList(orgContainer.getAllAbteilungsKuerzel()));

        firmSum.setText(getFirmSum());
        getSummaryOf();
    }

    /**
     * Beschriftet die Labels des Fenster je anch dem welche Abteilung
     * momentan in der ComboBox ausgewählt wurde
     *
     * @see this#getCountOfClass(Class, String)
     * @see this#getValueOfClass(Class, String)
     * @see this#getAbtSum(String)
     */
    @FXML
    protected void getSummaryOf(){
        String choosen = abteilungComboBox.getValue();
        Abteilung choosenAbt = orgContainer.getAbteilungByKuerzel(choosen);

        if(orgContainer.existingAbteilungKuerzel(choosen)) {
            abtName.setText(choosenAbt.getName());
            abtSum.setText(getAbtSum(choosen));

            countFuhrpark.setText("" + getCountOfClass(Fuhrpark.class, choosen));
            valueFuhrpark.setText(getValueOfClass(Fuhrpark.class, choosen));

            countBoden.setText("" + getCountOfClass(BodenUndGebaeude.class, choosen));
            valueBoden.setText("" +getValueOfClass(BodenUndGebaeude.class, choosen));

            countHardware.setText("" + getCountOfClass(Hardware.class, choosen));
            valueHardware.setText("" + getValueOfClass(Hardware.class, choosen));

            countSoftware.setText("" + getCountOfClass(Software.class, choosen));
            valueSoftware.setText("" +getValueOfClass(Software.class, choosen));

            countMobiliar.setText("" + getCountOfClass(Mobiliar.class, choosen));
            valueMobiliar.setText("" + getValueOfClass(Mobiliar.class, choosen));

            countSonstiges.setText("" + getCountOfClass(Sonstiges.class, choosen));
            valueSonstiges.setText("" + getValueOfClass(Sonstiges.class, choosen));
        }
    }

    /**
     * Zählt die Anzahl der vorkommnisse einer bestimmten Klasse in einer bestimmten abteilung
     *
     * @param _class Die Klasse die gezählt wird
     * @param abteilung Die abteilung in der gesucht wird
     * @return die Anzahl der vorkommnisse
     * @see AssetContainer#getSummaryOf(String, String, OrganisationContainer)
     */
    private int getCountOfClass(Class _class, String abteilung){
        AssetContainer container = new AssetContainer();
        int countForClass = 0;

        ArrayList<Asset> list = container.getSummaryOf(abteilung, path, orgContainer);

        for (Asset asset : list) {
            if (asset.getClass().equals(_class)) {
                if(asset.getAnzahl() <= 1) {
                    countForClass ++;
                } else {
                    countForClass += asset.getAnzahl();
                }
            }
        }
        return countForClass;
    }

    /**
     * Ermittelt den Gesamtwert der anlagegegenstände mit einer
     * bestimmten klasse in einer bestimmten abteilung und gibt ihn als String zurück
     *
     *
     * @param _class Die Klasse nach der gesucht wird
     * @param abteilung Die abteilung in der gesucht wird
     * @see AssetContainer#getSummaryOf(String, String, OrganisationContainer)
     * @return der Gesamt wert
     */
    private String getValueOfClass(Class _class, String abteilung){
        AssetContainer container = new AssetContainer();
        double valueForClass = 0;

        ArrayList<Asset> list = container.getSummaryOf(abteilung, path, orgContainer);

        for (Asset asset : list) {
            if (asset.getClass().equals(_class)) {
                if(asset.getAnzahl() <= 1) {
                    valueForClass += asset.getAnschaffungswert();
                } else {
                    valueForClass += asset.getAnschaffungswert() * asset.getAnzahl();
                }
            }
        }
        if(valueForClass != 0) {
            return german.format(valueForClass) + "€";
        } else {
            return "0,00€";
        }
    }

    /**
     * Ermittelt den Gesamtwert einer Abteilung und gibt ihn als String zurück
     *
     * @param abteilung die Abteilung dessen Wert gesucht wird
     * @see AssetContainer#getSummaryOf(String, String, OrganisationContainer)
     * @return Wert
     */
    private String getAbtSum(String abteilung){
        AssetContainer container = new AssetContainer();
        ArrayList<Asset> list = container.getSummaryOf(abteilung, path, orgContainer);
        double value = 0;
        for(Asset asset : list){
            if(asset.getAnzahl() <= 1){
                value += asset.getAnschaffungswert();
            } else {
                value += asset.getAnschaffungswert() * asset.getAnzahl();
            }
        }
        if(value != 0) {
            return german.format(value) + "€";
        } else {
            return "0,00€";
        }
    }

    /**
     * Ermittelt den Firmenweiten Wert und gibt ihn als String zurück
     * @return Firmenweiter gesamtwert
     * @see AssetContainer#getSummary(String)
     */
    private String getFirmSum(){
        AssetContainer container = new AssetContainer();
        ArrayList<Asset> list = container.getSummary(path);
        double value = 0;
        for(Asset asset : list){
            if(asset.getAnzahl() <= 1){
                value += asset.getAnschaffungswert();
            } else {
                value += asset.getAnschaffungswert() * asset.getAnzahl();
            }
        }
        if(value != 0) {
            return german.format(value) + "€";
        } else {
            return "0,00€";
        }
    }

    /**
     * übergibt parameter vom View zur Zusammenfassung
     *
     * @param path Speicherpfad
     */
    public void getParams(String path){
        this.path = path;
        initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
