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

public class SummaryController implements Initializable{
    @FXML
    private Label countFuhrpark = new Label();
    @FXML
    private Label valueFuhrpark;

    @FXML
    private Label countBoden;
    @FXML
    private Label valueBoden;

    @FXML
    private Label countSoftware;
    @FXML
    private Label valueSoftware;

    @FXML
    private Label countHardware;
    @FXML
    private Label valueHardware;

    @FXML
    private Label countSonstiges;
    @FXML
    private Label valueSonstiges;

    @FXML
    private Label firmSum;

    @FXML
    private Label abtSum;

    @FXML
    private ComboBox<String> abteilungComboBox;

    @FXML
    private Label abtName;

    private AssetContainer assetContainer;

    private OrganisationContainer orgContainer;

    private String path;

    private final java.text.DecimalFormatSymbols germany
            = new java.text.DecimalFormatSymbols( new java.util.Locale( "de", "DE" ));
    private final java.text.DecimalFormat german
            = new java.text.DecimalFormat( "##,###.00", germany );


    @FXML
    public void initialize(){
        orgContainer = new OrganisationContainer().loadOrganisationsData();

        abteilungComboBox.setValue("- kein Eintrag gewählt -");
        abteilungComboBox.setItems(FXCollections.observableArrayList(orgContainer.getAllAbteilungsKuerzel()));

        firmSum.setText(getFirmSum());
        getSummaryOf();
    }

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
            valueBoden.setText(getValueOfClass(BodenUndGebaeude.class, choosen));

            countHardware.setText("" + getCountOfClass(Hardware.class, choosen));
            valueHardware.setText(getValueOfClass(Hardware.class, choosen));

            countSoftware.setText("" + getCountOfClass(Software.class, choosen));
            valueSoftware.setText(getValueOfClass(Software.class, choosen));

            countSonstiges.setText("" + getCountOfClass(Sonstiges.class, choosen));
            valueSonstiges.setText(getValueOfClass(Sonstiges.class, choosen));
        }
    }

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

    public void getParams(String path){
        this.path = path;
        initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
