package GUI.ViewGUI;


import Data.Asset;
import Data.Person;
import GUI.Dialogs;
import GUI.ViewGUI.CellFactories.AnschaffungswertCellFactory;
import GUI.ViewGUI.CellFactories.InsDateCellFactory;
import GUI.ViewGUI.Comparators.AnschaffungswertComparator;
import GUI.ViewGUI.NewItemDialogs.AssetDialog;
import Verwaltung.AssetContainer;
import Verwaltung.OrganisationContainer;
import Verwaltung.UserContainer;
import GUI.StartGUI.StartController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Controller klasse für das Viewfenster welches für die UI der Assetverwaltung,
 * zuständig ist.
 *
 * @author Tim
 * @version 1.0
 */
public class ViewController implements Initializable {

    //ScrollPane für die Inventar einträge
    @FXML
    private ScrollPane ItemScrollPane;

    //Label in dem der Username des eingeloggten benutzers angezeigt wird
    @FXML
    private Label nameLabel;

    //Tabelle für die Asseteinträge
    @FXML
    private TableView<Asset> itemTable = new TableView<Asset>();

    //Tabellenspalte für die Inventarnummer
    @FXML
    private TableColumn NRColumn;

    //Tabellenspalte für die Bezeichnung
    @FXML
    private TableColumn bezColumn;

    //Tabellenspalte für Bearbeiten Buttons
    @FXML
    private TableColumn ActionColumn;

    //Tabellenspalte für den Anschaffungswert
    @FXML
    private TableColumn valueColumn;

    //Tabellenspalte für das Datum
    @FXML
    private TableColumn dateColumn;

    //Tabellenspalte für die Anzahl
    @FXML
    private TableColumn countColumn;

    //Button zum zurücksetzen der Filter
    @FXML
    private Button resetButton;

    //Container für die Userdaten
    private UserContainer userContainer;

    //Container für die asseteinträge
    private AssetContainer assetContainer = new AssetContainer();

    //Container für abteilungen und sachgebiete
    private OrganisationContainer orgContainer = new OrganisationContainer();

    //Container für die gefilterten Assets
    private ArrayList filteredList = new ArrayList();

    //Boolean der bestimmt ob Filter aktiv sind
    private boolean ActiveFilter = false;

    //aktueller speicherpfad
    private String path;

    //Name des Inventares
    private String invName;

    //Speicherpfad + dateinamen des abgespeicherten Container
    private String completePath;

    //momentan eingeloggter User
    private Person user;

    //Menu für die Summary Buttons
    @FXML
    private Menu summaryMenu;


    /**
     * Funktion die das View Initialisiert
     * läd das Inventar aus der datei und ruft die Funktion
     *
     *      fillTable();
     *
     * auf  um die tablle zu füllen
     *
     * @author Tim
     */
    @FXML
    protected void initialize() {
        completePath = path + "\\" + invName + ".Inv";
        File inventoryFile = new File(completePath);

        assetContainer = assetContainer.loadInventar(inventoryFile.getPath());
        itemTable.setPlaceholder(new Label("Keine Anlage-gegenstände gefunden"));

        ArrayList<Asset> arrayList = assetContainer.getAssetList();
        fillTable();
        for (int i = 0; i < orgContainer.getAnzahlAbt(); i++) {
            MenuItem menuItem = new MenuItem();
            menuItem.setText(orgContainer.getAllAbteilungsKürzel()[i]);
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    MenuItem menuItem = (MenuItem) event.getSource();
                    summary(menuItem.getText());
                }
            });
            summaryMenu.getItems().add(menuItem);
        }
    }


    private void summary(String abteilung){

    }

    /**
     * Füllt die Tabelle mit den daten aus dem Assetcontainer
     * hier ist auch die Klasse für die Editbuttons inline geschrieben
     *
     * @author Tim
     */
    private void fillTable(){
        ArrayList<Asset> arrayList;
        if(ActiveFilter){
            arrayList = filteredList;
            resetButton.setVisible(true);
        } else {
            arrayList = assetContainer.getAssetList();
            resetButton.setVisible(false);
        }

        NRColumn.setCellValueFactory(new PropertyValueFactory<>("inventarnummer"));
        bezColumn.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("anzahl"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<Asset,Double>("anschaffungswert"));
        valueColumn.setComparator(new AnschaffungswertComparator());
        valueColumn.setCellFactory(new AnschaffungswertCellFactory());
        dateColumn.setCellValueFactory(new PropertyValueFactory<Asset,Date>("inserierungsdatum"));
        dateColumn.setCellFactory(new InsDateCellFactory());

        ActionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Asset, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Asset, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });
        ActionColumn.setCellFactory(
                new Callback<TableColumn<Asset, Boolean>, TableCell<Asset, Boolean>>() {
                    @Override
                    public TableCell<Asset, Boolean> call(TableColumn<Asset, Boolean> p) {
                        return new ButtonCell();
                    }
                });

        NRColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        valueColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        countColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        bezColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        dateColumn.setStyle("-fx-alignment: CENTER;");

        ObservableList<Asset> list = FXCollections.observableArrayList(arrayList);

        itemTable.setItems(list);
    }

    /**
     * Private Klassendefinition für den editbutton in der Tabelle
     *
     * @author Tim
     */
    private class ButtonCell extends TableCell<Asset, Boolean> {
        final Button cellButton = new Button("        ");

        ButtonCell(){

            cellButton.getStylesheets().add("/GUI/style.css");
            cellButton.getStyleClass().add("editButton");

            //Aktion die ausgeführt wird wenn der Button gedrückt wurde
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // Das ausgewählte Asset bekommen
                  try {
                    Asset selectedAsset = (Asset) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    selectedAsset.display();

                    String assetClass = selectedAsset.getClass().toString().substring(11);
                    System.out.println(assetClass);

                    Asset editedAsset = new AssetDialog().getNewAsset(assetClass, selectedAsset).getKey();
                    editedAsset.display();

                    assetContainer.editItemById(selectedAsset.getInventarnummer(), editedAsset);
                    assetContainer.showAll();

                    fillTable();
                  } catch (Exception e){
                    System.out.println("[INFO] Cancel Button Clicked");
                  }
                }
            });
        }

        //Zeige den Button nur wenn die tabellenreihe nicht leer ist
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }

    /**
     * Setzt alle Filter zurück
     *
     * @author Tim
     */
    @FXML
    protected void resetFilter(){
        ActiveFilter=false;
        filteredList = null;

        fillTable();
        System.out.println("[INFO] Filter zurückgesetzt!");
    }

    /**
     * logik für das anlegen eines neuen Asset
     * @param event
     */
    @FXML
    protected void addAssetClicked(ActionEvent event) {
        String assetType = askForAssetType();
        if(assetType != null && assetType.equals("Boden und Gebäude")){
            assetType = "BodenUndGebaeude";
        }
        if (assetType != "" && assetType != null) {
            while (true) {
                Pair pair = new AssetDialog().getNewAsset(assetType, null);
                if (pair.getValue() == null && pair.getKey() != null) {
                    Asset b = (Asset) pair.getKey();
                    assetContainer.insertAsset(b);
                    break;
                } else if (pair.getKey() == null && pair.getValue() == null) {
                    break;
                } else {
                    System.out.println(pair.getValue());
                }
            }
            fillTable();
        }
    }

    /**
     * Methode zur parameterübergabe von Controller zu Controller
     *
     * @param inventoryName -> Name des Inventars
     * @param path -> pfad der speicherung
     * @param userContainer -> container der userdaten
     * @param user -> momentan eingeloggter user
     */
    public void getParams(String inventoryName, String path, UserContainer userContainer, Person user, OrganisationContainer organisationContainer){
        nameLabel.setText("Eingeloggt als: " + user.getUsername());
        this.path = path;
        this.invName = inventoryName;
        this.userContainer = userContainer;
        this.user = user;
        this.orgContainer = organisationContainer;
        initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * Methode die ein Fenster aufbaut welches nach dem anzulegenden Assettypfragt
     *
     * @return
     */
    private String askForAssetType(){
        List<String> choices = new ArrayList<>();
        AssetContainer a = new AssetContainer();
        for(int i = 0; i < a.getExistingAssetTypes().length; i++) {
            choices.add(a.getExistingAssetTypes()[i]);
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Boden und Gebäude", choices);
        dialog.setTitle("Item Anlegen");
        dialog.setHeaderText("Art des Gegenstandes wählen!");
        dialog.setContentText("Arten:");
        dialog.getDialogPane().setStyle("-fx-background-color:  #e6f9ff");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("[INFO] Item typ gewählt: " + result.get());
            return result.get();
        }
        return null;
    }

    /**
     * Methode die aufgerufen wird wenn speichern und beenden gedrückt wird
     *
     * @param event
     */
    @FXML
    protected void backClicked(ActionEvent event){

        assetContainer.safeInventar(completePath);

        Stage lastWindow = (Stage) nameLabel.getScene().getWindow();
        lastWindow.hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/StartGUI/StartStyle.fxml"));
        Parent root = null;

        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StartController controller = loader.getController();
        controller.getParams(path,userContainer,user);

        Stage stage = new Stage();
        stage.setTitle("Inventarverwaltung 1.0");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Fragt nach den Anzuwendenden Filter und übergibt sie an den AssetContainer
     * Die antwort ist eine gefilterte Liste die ans das ViewController objekt über-
     * geben wird.
     *
     * der boolean activefilter bestimmt ob beim aufruf von fillTable() die gefilterte Liste
     * angezeigt wird oder nich
     *
     * @author Tim
     */
    @FXML
    protected void filterClicked (){
        boolean[] filter = Dialogs.getFilter();
        if(filter != null){
            ActiveFilter = true;
            filteredList = assetContainer.getAssetsByFilter(filter);
        } else {
            ActiveFilter = false;
            filteredList = null;
        }
        fillTable();
    }
}
