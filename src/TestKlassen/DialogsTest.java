package TestKlassen;

import Data.Asset;
import GUI.Dialogs;
import GUI.ViewGUI.NewItemDialogs.AssetDialog;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 *
 */

public class DialogsTest extends Application{
    public static void main (String[] args){
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Asset b = null;
        new AssetDialog().getNewAsset("Boden und Gebäude", b);


        boolean[] a = Dialogs.getFilter();

        if(a[0]){
            System.out.println(0);
        }

        if(a[1]){
            System.out.println(1);
        }

        if(a[2]){
            System.out.println(2);
        }

        if(a[3]){
            System.out.println(3);
        }

        if(a[4]){
            System.out.println(4);
        }

        if(a[5]){
            System.out.println(5);
        }
    }
}
