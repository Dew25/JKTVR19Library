/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Reader;
import java.util.Scanner;

import tools.savers.ReadersStorageManager;

/**
 *
 * @author Melnikov
 */
public class ReaderManager {
private ReadersStorageManager readersStorageManager = new ReadersStorageManager();
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

    public void addReaderToArray(Reader reader, Reader[] readers) {
        for (int i = 0; i < readers.length; i++) {
            if(readers[i] == null){
                readers[i] = reader;
                break;
            }
        } 
        readersStorageManager.saveReadersToFile(readers);
    }

    public void printListReaders(Reader[] readers) {
        int n = 0;
        for (Reader r : readers) {
            if(r != null){
                System.out.println(n+1+". "+r.toString());
                n++;
            }
        }
    }
    
}
