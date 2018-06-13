package GUI.StartGUI;

import Verwaltung.UserContainer;

import javax.swing.*;
import java.awt.*;

public class DeleteUserJPanel extends JPanel{

    JButton okButton = new JButton();
    int numberOfUser;
    JCheckBox[] checkBoxes;

    public DeleteUserJPanel(UserContainer container){
        setLookAndFeel();
        numberOfUser = container.getNumberOfUser();
        checkBoxes = new JCheckBox[numberOfUser];
        for(int i = 0 ; i < numberOfUser; i++){
            checkBoxes[i] = new JCheckBox(container.getUserName(i));
        }


        this.setLayout(new GridLayout(2,1));

        for(int i = 0; i < numberOfUser; i++) {
            this.add(checkBoxes[i]);
        }
    }

    public String[] getPickedUser(){
        int anz = 0;
        for(int i = 0; i < numberOfUser; i++){
            if(checkBoxes[i].isSelected()){
                anz++;
            }
        }
        String[] pickedUser = new String[anz];
        anz = 0;
        for(int i = 0; i < numberOfUser; i++ ){
            if(checkBoxes[i].isSelected()){
                pickedUser[anz] = checkBoxes[i].getText();
                anz++;
            }
        }
        return pickedUser;
    }
    private void setLookAndFeel() {
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
