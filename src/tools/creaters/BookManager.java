/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Book;
import entity.controllers.BookController;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
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
    
    public boolean printListBooks() {
        BookController bc = new BookController();
        List<Book> listBooks = bc.findAll();
        if(listBooks == null || listBooks.size() < 1){
            System.out.println("Книг нет!");
            return false;
        }
        for (Book b : listBooks) {
            if(b != null){
                System.out.println(b.getId()+". "+b.toString());
            }
        }
        return true;
    }
    
}
