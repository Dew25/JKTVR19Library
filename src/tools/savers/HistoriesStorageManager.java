/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.savers;

import entity.History;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 *
 * @author Melnikov
 */
public class HistoriesStorageManager {

    public void saveHistoriesToFile(History[] histories) {
        String fileName = "histories";
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(histories);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.println("Нет такого файла");
        } catch (IOException ex) {
            System.out.println("Ошибка ввода/вывода");
        }
    }
    public History[] loadHistoriesFromFile() {
        //Book[] books = new book[10];
        History[] histories = null;
        String fileName = "histories";
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            return (History[]) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("Нет такого файла"); 
        } catch (IOException ex){
            System.out.println("Ошибка ввода/вывода");
        } catch (ClassNotFoundException ex) {
            System.out.println("Нет такого класса");
        }
        return histories;
    }
    
}
