package GUI.ViewGUI.Summary;


import Data.Abteilung;
import Data.Asset;
import Data.Fuhrpark;
import Data.Sachgebiet;
import Verwaltung.AssetContainer;
import Verwaltung.OrganisationContainer;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.File;
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
    private Label valueHoleFirm;

    @FXML
    private ComboBox<String> abteilungComboBox;

    private AssetContainer assetContainer;

    private OrganisationContainer orgContainer;

    private String path;


    @FXML
    public void initialize(){
        System.out.println("TEST!$   " + path);
        System.out.println(orgContainer.getAllAbteilungsKuerzel()[0]);

        abteilungComboBox.setValue("- kein Eintrag gewählt -");
        abteilungComboBox.setItems(FXCollections.observableArrayList(orgContainer.getAllAbteilungsKuerzel()));
        //getSummaryOf();
    }

    @FXML
    protected void getSummaryOf(){
        System.out.println(Fuhrpark.class);
        System.out.println(abteilungComboBox.getValue());
        countFuhrpark.setText("" + getCountOfClass(Fuhrpark.class, abteilungComboBox.getValue()));
        //countFuhrpark.setText("20");
    }

    private int getCountOfClass(Class _class, String abteilung){
        Abteilung abt = orgContainer.getAbteilungByKuerzel(abteilung);
        AssetContainer container = new AssetContainer();

        int countForClass = 20;

        System.out.println(path);

        /*
        ArrayList<Asset> list = container.getSummaryOf(abteilung,path,orgContainer);


        for(Asset asset : list){
            if(asset.getClass().equals(_class)){
                countForClass++;
            }
        }
        */
        return countForClass;
    }

    public void getParams(String path){
        this.path = path;
        orgContainer = new OrganisationContainer().loadOrganisationsData();
        initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
