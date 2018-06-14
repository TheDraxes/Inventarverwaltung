package GUI.ViewGUI;

import Item.Fuhrpark;
import Item.Item;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/*
    Controller Klasse für das View Fenster

    Hier werden Methoden geschrieben die durch Interaktion mit dem ViewGUI getriggered werden

    Der Style des Start Fenster ist in der FXML Datei "ViewStyle" Implementiert (SceneBuilder Tool)
*/

public class ViewController implements Initializable {

    //ScrollPane für die Inventar einträge. Hier müssen nur die ItemEntry Objekte angeheftet werden
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

    private UserContainer userContainer;

    private String path;

    private String user;

    @FXML
    public void initialize(){
        ArrayList<Item> a = new ArrayList<Item>();

        for(int i = 0 ; i < 4; i++){
            Fuhrpark b = new Fuhrpark();
            b.setInventarnr(100 + i);
            b.setItembez("BMW Coupe");
            a.add(b);
        }

        NRColumn.setCellValueFactory(new PropertyValueFactory<>("Inventarnr"));
        bezColumn.setCellValueFactory(new PropertyValueFactory<>("itembez"));
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


        ObservableList<Item> list = FXCollections.observableArrayList(a);

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
                    //remove selected item from the table list
                    System.out.println(selectedItem.getInventarnr());
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
        Stage a = (Stage) nameLabel.getScene().getWindow();


    }

    public void getParams(String inventoryName, String path, UserContainer userContainer, String user){
        nameLabel.setText("Eingeloggt als: " + user);
        this.path = path;
        this.userContainer = userContainer;
        this.user = user;
        initialize();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void backClicked(ActionEvent event){

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
