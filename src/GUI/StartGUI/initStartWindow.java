package GUI.StartGUI;

import Data.Person;
import Verwaltung.UserContainer;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

/**
 * Klasse die den userContainer und den Namen des Users aus dem Loginfenster
 * übergeben bekommt.
 *
 * Sie ist dafür zuständig das Inventarverwaltungsfenster aufzubauen und mit den
 * nötigen Informationen zu versorgen.
 *
 * @author Tim
 * @version 1.0
 */
public class initStartWindow {
    /**
     * Konstruktor
     *
     * liest den speicherpfad für die Inventare ein. Wenn die datei für den gespeicherten pfad
     * nicht vorhanden ist, wird nach einem Pfad gefragt und eine neue Datei erstellt.
     *
     * @see #readPath(File) {@link #askForPath()}
     * @param userContainer Container zur Userdaten verwaltung
     * @param user name des eingeloggten Users
     */
    public initStartWindow(UserContainer userContainer, Person user) {
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
        root.setStyle("-fx-background-color: #b5edff");
        StartController controller = loader.getController();
        controller.getParams(path,userContainer,user);

        Stage stage = new Stage();
        stage.setTitle("Inventarverwaltung 1.0");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *
     * Liest ein String Object aus einer Datei
     *
     * Wird benutzt um den Abgespeicherten Speicherpfad einzulesen
     *
     * @param safe  File Objekt welches den Pfad zu der startUp.dat enthält
     * @return String
     */
    private static String readPath(File safe){
        try {
            FileInputStream inStream = new FileInputStream(safe);
            ObjectInputStream obStream = new ObjectInputStream(inStream);
            return (String) obStream.readObject();
        } catch (IOException|ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Baut das Pfadauswahl fenster auf
     * leider noch Swing
     *
     * @return der pfad der vom benutzer beim ersten starten eingegeben wird
     */

    private static String askForPath(){

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

    /**
     * setzt das Look and Feel für Swing elemente
     * müsste noch durch Javafx ersetzt werden
     */
    private static void setLookAndFeel(){
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

