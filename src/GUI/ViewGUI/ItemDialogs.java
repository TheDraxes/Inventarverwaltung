package GUI.ViewGUI;

import Data.Fuhrpark;
import Data.Item;
import GUI.Dialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ComboBox kw_ps;

    String[] labelNames = new String[0];
    String itemType;

    public ItemDialogs(){
        ObservableList<String> os = FXCollections.observableArrayList("Kw", "Ps");
        this.kw_ps = new ComboBox(os);
        this.kw_ps.setValue("Kw");
    }

    public Pair<Item, Boolean> getNewItem(String itemTyp){
        this.itemType = itemTyp;
        setTextFields(itemType);
        if(itemTyp != null){
            switch(itemTyp){
                case "Fuhrpark":
                    Hashtable<String,String> newItemData = buildNewFurhparkWindow();
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

            System.out.println(labelNames[0] + " " + newItemData.get(labelNames[0]));
            System.out.println(labelNames[1] + " " + newItemData.get(labelNames[1]));
            System.out.println(labelNames[2] + " " + newItemData.get(labelNames[2]));
            System.out.println(labelNames[3] + " " + newItemData.get(labelNames[3]));
            System.out.println(labelNames[4] + " " + newItemData.get(labelNames[4]));
            System.out.println(labelNames[5] + " " + newItemData.get(labelNames[5]));
            System.out.println(labelNames[6] + " " + newItemData.get(labelNames[6]));

            newFuhrpark.setBezeichnung(newItemData.get(labelNames[0]));
            double wert = Double.parseDouble(newItemData.get(labelNames[1]));
            newFuhrpark.setAnschaffungswert(wert);
            int tnd = Integer.parseInt(newItemData.get(labelNames[2]));
            newFuhrpark.setTnd(tnd);
            int anzahl = Integer.parseInt(newItemData.get(labelNames[3]));
            newFuhrpark.setAnzahl(anzahl);

            if(!newItemData.get(labelNames[4]).equals("")){
                newFuhrpark.setKennzeichen(newItemData.get(labelNames[4]));
            }

            if(!newItemData.get(labelNames[5]).equals("")){
                long gestellNR = Long.parseLong(newItemData.get(labelNames[5]));
                newFuhrpark.setFahrgestellnummer(gestellNR);
            }

            if(!newItemData.get(labelNames[6]).equals("")){
                int kmstand = Integer.parseInt(newItemData.get(labelNames[6]));
                newFuhrpark.setKilometerstand(kmstand);
            }

            String input = newItemData.get(labelNames[7]);

            if(!input.equals("")){
                String type = input.substring(input.length()-2,input.length());
                String power = input.substring(0,input.length()-2);

                if(type.equals("Ps")){
                    newFuhrpark.setPs(Integer.parseInt(power));
                } else {
                    newFuhrpark.setKw(Integer.parseInt(power));
                }
            }

            newFuhrpark.setInserierungsdatum(new Date(System.currentTimeMillis()));
            return newFuhrpark;
        } else {
            return null;
        }
    }

    public void setTextFields(String itemType){
        switch (itemType){
            case "Fuhrpark": this.labelNames = new Fuhrpark().getParamNames();
                             break;
        }
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

    public Hashtable<String, String> buildNewFurhparkWindow(){
        Dialog<Hashtable<String,String>> dialog = new Dialog<>();

        dialog.setTitle("Neues Item");
        ButtonType addButton = new ButtonType("Hinzufügen" ,ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);
        dialog.getDialogPane().setStyle("-fx-background-color:  #b5edff");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20,150,10,10));

        for(int i = 0; i < TextFields.length-1; i++) {
            grid.add(Labels[i], 0, i);
            grid.add(TextFields[i], 1, i);
        }

        grid.add(Labels[Labels.length - 1],0,Labels.length);
        grid.add(TextFields[TextFields.length - 1],1,TextFields.length);
        grid.add(kw_ps,2,TextFields.length);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == addButton) {
                        Hashtable<String, String> hashtable = new Hashtable<>();
                        for (int i = 0; i < TextFields.length - 1; i++) {
                            hashtable.put(labelNames[i], TextFields[i].getText());
                        }

                        if (((String) kw_ps.getValue()).equals("Kw")) {
                            hashtable.put(labelNames[labelNames.length - 1], (TextFields[TextFields.length - 1].getText()) + "Kw");
                        } else {
                            hashtable.put(labelNames[labelNames.length - 1], (TextFields[TextFields.length - 1].getText()) + "Ps");
                        }
                        return hashtable;
                    } else if (dialogButton == ButtonType.CANCEL) {
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
