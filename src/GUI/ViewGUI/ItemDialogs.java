package GUI.ViewGUI;

import javafx.scene.control.TextField;

public class ItemDialogs {
    TextField[] TextFields = new TextField[0];

    public ItemDialogs(String itemTyp){
        if(itemTyp != null){
            switch(itemTyp){
                case "Fuhrpark":
                    System.out.println("TESTETSTESTETSTTE");

                    break;
            }
        }
    }

    public void setTextFieldsForFuhrpark(){

    }

}
