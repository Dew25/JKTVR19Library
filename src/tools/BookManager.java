/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import entity.Book;
import entity.Reader;
import java.util.Scanner;

/**
 *
 * @author Melnikov
 */
public class BookManager {

    public Book addBook() {
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
    
}
