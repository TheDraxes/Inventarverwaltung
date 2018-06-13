package Verwaltung;

import javax.swing.*;
import java.io.*;

public class UserContainer implements Serializable{
    String[][] userData = new String[0][2];
    private int numberOfUser = 0;


    public String[][] getUserData() {
        return userData;
    }

    public void setUserData(String[][] userData) {
        this.userData = userData;
    }

    public int getNumberOfUser() {
        return numberOfUser;
    }

    public void setNumberOfUser(int numberOfUser) {
        this.numberOfUser = numberOfUser;
    }

    public void insertUser(String username, String passwort){
        String[][] old = this.userData;
        this.userData = new String[numberOfUser+1][2];
        for(int i = 0; i < numberOfUser; i++){
            userData[i][0] = old[i][0];
            userData[i][1] = old[i][1];
        }
        this.userData[numberOfUser][0] = username;
        this.userData[numberOfUser][1] = passwort;

        numberOfUser++;

        System.out.println("**User " + username + " erstellt");
    }

    public boolean userIsDuplicate(String username){
        for(int i = 0; i < numberOfUser; i++){
            if(userData[i][0].equals(username)){
                System.out.println(userData[i][0]);
                System.out.println(username);
                return true;
            }
        }
        return false;
    }

    public String getUserName(int index){
        return this.userData[index][0];
    }

    public int getIndexOfUser(String username){
        for(int i = 0; i < numberOfUser; i++){
            if(userData[i][0].equals(username)){
                return i;
            }
        }
        return -1;
    }

    public String getUserPasswort(int index){
        return this.userData[index][1];
    }

    public void safeUserData(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("user.dat");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(this);
            System.out.println("**Userdaten abgespeichert in user.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("User: ");
        System.out.println("======");

        for(int i = 0; i < numberOfUser; i++){
            System.out.println(userData[i][0] + " " + userData[i][1]);
        }
        System.out.println();
    }

    public UserContainer loadUserData(){
        FileInputStream fileInputStream = null;
        File userLogins = new File("user.dat");
        UserContainer loaded = new UserContainer();
        if(userLogins.exists()) {
            try {
                fileInputStream = new FileInputStream(userLogins);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                loaded = (UserContainer) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return loaded;
    }

    public void deleteUser(String username){

        setLookAndFeel();

        int result = JOptionPane.showConfirmDialog(null,"User " + username + " wirklich löschen?");

        int index = getIndexOfUser(username);

        if(result == JOptionPane.OK_OPTION){
            String[][] old = this.userData;
            String[][] newArray = new String[this.numberOfUser-1][2];
            if(index > -1){
                for(int i = 0; i <= index-1; i++){
                    newArray[i][0] = old[i][0];
                    newArray[i][1] = old[i][1];
                }
                if(index < numberOfUser-1) {
                    newArray[index][0] = old[index + 1][0];
                    newArray[index][1] = old[index + 1][1];
                }
                if(index+1 <= numberOfUser-1) {
                    for (int i = index + 1; i < numberOfUser - 1; i++) {
                        newArray[i][0] = old[i + 1][0];
                        newArray[i][1] = old[i + 1][1];
                    }
                }
                numberOfUser--;
                userData = newArray;
            } else {
                System.out.println("User nicht gefunden!");
            }
        } else if(result == JOptionPane.CANCEL_OPTION || result == JOptionPane.NO_OPTION){
            System.out.println("Vorgang abgebrochen!");
        }
        safeUserData();
    }

    public void printAllUser(){
        for (int i = 0; i < numberOfUser; i++){
            System.out.println(userData[i][0]);
        }
    }
    private void setLookAndFeel(){
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
