/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class UserCardManager {

    public History giveBook(Book[] books, Reader[] readers) {
        History history = null;
        System.out.println("--- Список читателей ---");
        int n = 0;
        for (Reader r : readers) {
            if(r != null){
                System.out.println(n+1+". "+r.toString());
                n++;
            }
        }
         System.out.print("Выберите номер читателя: ");    
         Scanner scanner = new Scanner(System.in);
         int readerNumber = scanner.nextInt();
         Reader reader = readers[readerNumber - 1];
         System.out.println("--- Список книг ---");
                    int j = 0;
                    for (Book b : books) {
                        if(b != null){
                            System.out.println(j+1+". "+b.toString());
                            j++;
                        }
                    }
         System.out.print("Выберите номер книги: ");    
         int bookNumber = scanner.nextInt();
         Book book = books[bookNumber - 1];
         Calendar calendar = new GregorianCalendar();
         history.setBook(book);
         history.setReader(reader);
         history.setTakeOnDate(calendar.getTime());
        return history;
    }

   
    
}
