package GUI.StartGUI;

import GUI.LoginGUI.UserContainer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

/*
    Klasse die sich darum kümmert das im Scene Builder erstellte StartFenster Über die StartStyle.fxml einzulesen
    und aufzubauen.
*/
public class showStartWindow {

    public showStartWindow(UserContainer userContainer) {
        String path;
        File startUp = new File("startUp.dat");
        if(startUp.exists()){
            path = readPath(startUp);
        } else {
            path = askForPath();
            try {
                FileOutputStream outputStream = new FileOutputStream(startUp);
                ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
                objectOutput.writeObject(path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/StartGUI/StartStyle.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StartController controller = loader.getController();
        controller.getPath(path);
        controller.getUserContainer(userContainer);

        Stage stage = new Stage();
        stage.setTitle("Inventarverwaltung 1.0");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();

        /*
        Parent root = FXMLLoader.load(getClass().getResource("StartStyle.fxml"));
        primaryStage.setTitle("Inventarverwaltung 1.0");
        Scene a = new Scene(root);
        primaryStage.setScene(a);
        primaryStage.show();
        */

    }

    public static String readPath(File safe){
        try {
            FileInputStream inStream = new FileInputStream(safe);
            ObjectInputStream obStream = new ObjectInputStream(inStream);
            return (String) obStream.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String askForPath(){

        setLookAndFeel();
        JOptionPane.showMessageDialog(null,"Pfad zur Inventarverwaltung nicht gefunden! Bitte wähle einen Pfad aus!");

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Speicherpfad für die Inventarverwaltung");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = fc.showOpenDialog(null);
        File f;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            return f.getPath();
        } else {
            return null;
        }
    }

    public static void setLookAndFeel(){
        String laf = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(laf);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}

