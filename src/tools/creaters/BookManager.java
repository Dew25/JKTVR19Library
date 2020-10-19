/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Book;
import java.util.Scanner;

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
        System.out.println("Введите год издания:");
        book.setPublishedYear(scanner.nextInt());
        return book;
    }

    public void addBookToArray(Book book, Book[] books) {
        for (int i = 0; i < books.length; i++) {
            if(books[i] == null){
                books[i] = book;
                break;
            }
        }    
    }

    public boolean printListBooks(Book[] books) {
        if(books == null || books.length < 1){
            System.out.println("Книг нет!");
            return false;
        }
        int j = 0;
        for (Book b : books) {
            if(b != null){
                System.out.println(j+1+". "+b.toString());
                j++;
            }
        }
        return true;
    }
    
}
