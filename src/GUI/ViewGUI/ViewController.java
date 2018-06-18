package GUI.ViewGUI;


import Data.Item;
import Verwaltung.ItemContainer;
import Verwaltung.UserContainerAlt;
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

/*
    Controller Klasse für das View Fenster

    Hier werden Methoden geschrieben die durch Interaktion mit dem ViewGUI getriggered werden

    Der Style des Start Fenster ist in der FXML Datei "ViewStyle" Implementiert (SceneBuilder Tool)
*/

public class ViewController implements Initializable {

    //ScrollPane für die Inventar einträge
    @FXML
    private ScrollPane ItemScrollPane;

    @FXML
    private Label nameLabel;

    @FXML
    private TableView<Item> itemTable = new TableView<Item>();

    @FXML
    private TableColumn NRColumn;

    @FXML
    private TableColumn bezColumn;

    @FXML
    private TableColumn ActionColumn;

    @FXML
    private TableColumn valueColumn;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn countColumn;

    private UserContainerAlt userContainer;

    private ItemContainer itemContainer = new ItemContainer();

    private String path;

    private String invName;

    private String completePath;

    private String user;

    @FXML
    public void initialize(){ ;
        completePath = path + "\\" + invName + ".Inv";

        File a = new File(completePath);

        itemContainer = itemContainer.loadInventar(a.getPath());

        ArrayList<Item> arrayList = itemContainer.getItemList();

        fillTable();

    }
    public void fillTable(){
        ArrayList<Item> arrayList = itemContainer.getItemList();


        NRColumn.setCellValueFactory(new PropertyValueFactory<>("inventarnummer"));
        bezColumn.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("anzahl"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("anschaffungswertString"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("inserierungsdatumString"));

        ActionColumn.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Item, Boolean>,
                        ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Item, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });
        ActionColumn.setCellFactory(
                new Callback<TableColumn<Item, Boolean>, TableCell<Item, Boolean>>() {
                    @Override
                    public TableCell<Item, Boolean> call(TableColumn<Item, Boolean> p) {
                        return new ButtonCell();
                    }
                });


        ObservableList<Item> list = FXCollections.observableArrayList(arrayList);

        itemTable.setItems(list);
    }

    //Define the button cell
    private class ButtonCell extends TableCell<Item, Boolean> {
        final Button cellButton = new Button("        ");

        ButtonCell(){

            cellButton.getStylesheets().add("/GUI/style.css");
            cellButton.getStyleClass().add("editButton");

            //Action when the button is pressed
            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // get Selected Item
                    Item selectedItem = (Item) ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());

                    selectedItem.display();
                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }

    //Methode die ausgeführt wird wenn der "Inventar anzeigen" Button in der Menueleiste Gedrückt wird
    //Hier werden an das ScrollPane Beispielhaft Platzhalter angehängt
    @FXML
    void addItemClicked(ActionEvent event) {
        String itemType = askForItemType();
        while(true) {
            Pair<Item, Boolean> a = new ItemDialogs().getNewItem(itemType);
            if(a.getKey() == null || a.getValue().equals("")) {

            } else if (a.getValue()) {
                itemContainer.insertItem(a.getKey());
                break;
            } else {
                System.out.println("**Vorgang abgebrochen!");
                break;
            }
        }

        fillTable();
    }

    public void getParams(String inventoryName, String path, UserContainerAlt userContainer, String user){
        nameLabel.setText("Eingeloggt als: " + user);
        this.path = path;
        this.invName = inventoryName;
        this.userContainer = userContainer;
        this.user = user;
        initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    protected String askForItemType(){
        List<String> choices = new ArrayList<>();
        ItemContainer a = new ItemContainer();
        for(int i = 0; i < a.getExistingItemTypes().length; i++) {
            choices.add(a.getExistingItemTypes()[i]);
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>("Fuhrpark", choices);
        dialog.setTitle("Item Anlegen");
        dialog.setHeaderText("Art des Gegenstandes wählen!");
        dialog.setContentText("Arten:");
        dialog.getDialogPane().setStyle("-fx-background-color:  #e6f9ff");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("**Item typ gewählt: " + result.get());
            return result.get();
        }
        return null;
    }

    @FXML
    void backClicked(ActionEvent event){

        itemContainer.safeInventar(completePath);
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
}
