/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Reader;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import tools.savers.StorageManager;


/**
 *
 * @author Melnikov
 */
public class ReaderManager {
private StorageManager storageManager = new StorageManager();
    public Reader createReader() {
        Reader reader = new Reader();
        System.out.println("--- Зарегистрировать читателя ---");
        System.out.println("Введите имя:");
        Scanner scanner = new Scanner(System.in);
        reader.setFirstname(scanner.nextLine());
        System.out.println("Введите фамилию:");
        reader.setLastname(scanner.nextLine());
        System.out.println("Введите телефон:");
        reader.setPhone(scanner.nextLine());
        return reader;
    }

    public void addReaderToArray(Reader reader, List<Reader> listReaders) {
        listReaders.add(reader);
        storageManager.save(listReaders,App.storageFile.READERS.toString());
    }

    public void printListReaders(List<Reader> listReaders) {
        int n = 0;
        for (Reader r : listReaders) {
            if(r != null){
                System.out.println(n+1+". "+r.toString());
                n++;
            }
        }
    }
    
}
