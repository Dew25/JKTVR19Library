/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Book;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import tools.savers.DBManager;
import tools.savers.FileManager;
import tools.savers.StorageManagerInterface;


/**
 *
 * @author Melnikov
 */
public class BookManager {

    public Book createBook() {
        Book book = new Book();
        System.out.println("--- Добавить книгу ---");
        System.out.println("Введите название:");
        Scanner scanner = new Scanner(System.in);
        book.setName(scanner.nextLine());
        System.out.println("Введите автора:");
        book.setAuthor(scanner.nextLine());
        int numPublishedYear;
        do{
            System.out.println("Введите год издания:");
            String strPublichedYear = scanner.nextLine();
            try {
                numPublishedYear = Integer.parseInt(strPublichedYear);
                break;
            } catch (Exception e) {
                System.out.println("Вводите цифры!");
            }
        }while(true);
        book.setPublishedYear(numPublishedYear);
        return book;
    }

    public void addBookToArray(Book book, List<Book> listBooks, StorageManagerInterface storageManager) {
       listBooks.add(book);
       storageManager.save(listBooks,App.storageFile.BOOKS.toString());
       
    }

    public boolean printListBooks(List<Book> listBooks) {
        if(listBooks == null || listBooks.size() < 1){
            System.out.println("Книг нет!");
            return false;
        }
        int j = 0;
        for (Book b : listBooks) {
            if(b != null){
                System.out.println(j+1+". "+b.toString());
                j++;
            }
        }
        return true;
    }
    
}
