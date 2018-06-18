package GUI.ViewGUI;

import Data.Fuhrpark;
import Data.Item;
import GUI.Dialogs;
import GUI.StartGUI.StartController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Date;
import java.util.Hashtable;
import java.util.Optional;

public class ItemDialogs {
    TextField[] TextFields = new TextField[0];
    Label[] Labels = new Label[0];
    String[] labelNames = new String[0];
    String itemType;

    public ItemDialogs(){

    }

    public Pair<Item, Boolean> getNewItem(String itemTyp){
        if(itemTyp != null){
            switch(itemTyp){
                case "Fuhrpark":
                    setTextFieldsForFuhrpark();
                    Hashtable<String,String> newItemData = buildNewItemWindow();
                    if(newItemData == null){
                        return new Pair<>(new Fuhrpark(),false);
                    }
                    return new Pair<>(getNewFuhrpark(newItemData),true);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }
    public Item getNewFuhrpark(Hashtable<String,String> newItemData){
        Fuhrpark newFuhrpark = new Fuhrpark();
        if(newItemData != null) {
            if (newItemData.get(labelNames[0]).equals("") ||
                    newItemData.get(labelNames[1]).equals("") ||
                    newItemData.get(labelNames[2]).equals("") ||
                    newItemData.get(labelNames[3]).equals("")) {
                Dialogs.warnDialog("Alle mit einem * markierten Felder müssen Ausgefüllt werden!","Warnung");
                return null;
            }

            newFuhrpark.setBezeichnung(newItemData.get(labelNames[0]));

            if (!newItemData.get(labelNames[1]).equals("")) {
                double wert = Double.parseDouble(newItemData.get(labelNames[1]));
                newFuhrpark.setAnschaffungswert(wert);
            }

            if (!newItemData.get(labelNames[2]).equals("")) {
                int tnd = Integer.parseInt(newItemData.get(labelNames[2]));
                newFuhrpark.setTnd(tnd);
            }

            if (!newItemData.get(labelNames[3]).equals("")) {
                int anzahl = Integer.parseInt(newItemData.get(labelNames[3]));
                newFuhrpark.setAnzahl(anzahl);
            }

            newFuhrpark.setInserierungsdatum(new Date(System.currentTimeMillis()));
            return newFuhrpark;
        } else {
            return null;
        }
    }

    public void setTextFieldsForFuhrpark(){
        this.labelNames = new Fuhrpark().getParamNames();
        Labels = new Label[labelNames.length];

        for(int i = 0; i < labelNames.length; i++){
            if(i < 4) {
                Labels[i] = new Label(labelNames[i] + "*");
            } else {
                Labels[i] = new Label(labelNames[i]);
            }
        }

        TextFields = new TextField[labelNames.length];
        for(int i = 0; i < labelNames.length; i++){
            TextFields[i] = new TextField();
            TextFields[i].setPromptText(Labels[i].getText());
        }
    }

    public Hashtable<String, String> buildNewItemWindow(){
        Dialog<Hashtable<String,String>> dialog = new Dialog<>();
        
        dialog.setTitle("Neues Item");
        ButtonType addButton = new ButtonType("Hinzufügen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
        dialog.getDialogPane().setStyle("-fx-background-color:  #b5edff");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));
        for(int i = 0; i < TextFields.length; i++) {
            grid.add(Labels[i], 0, i);
            grid.add(TextFields[i], 1, i);
        }

        dialog.getDialogPane().setContent(grid);


        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == addButton){
                Hashtable<String,String> hashtable = new Hashtable<>();
                for(int i = 0; i < TextFields.length; i++){
                    hashtable.put(labelNames[i],TextFields[i].getText());
                }
                return hashtable;
            } else if(dialogButton == ButtonType.CANCEL){
                return null;
            }
            return new Hashtable<>();
        });

        Optional<Hashtable<String,String >> result = dialog.showAndWait();

        if(result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

}
