package TestKlassen;

import GUI.Dialogs;
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
        boolean[] a = Dialogs.getFilter();

        System.out.println("" + a[0]);
        System.out.println("" + a[1]);
        System.out.println("" + a[2]);
        System.out.println("" + a[3]);
        System.out.println("" + a[4]);
        System.out.println("" + a[5]);

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
