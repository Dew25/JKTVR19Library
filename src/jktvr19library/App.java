/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19library;

import tools.savers.ReadersStorageManager;
import tools.creaters.ReaderManager;
import entity.Reader;
import entity.Book;
import entity.History;
import entity.User;
import java.util.Scanner;
import security.SecureManager;
import tools.creaters.BookManager;
import tools.savers.BooksStorageManager;
import tools.savers.HistoriesStorageManager;
import tools.creaters.UserCardManager;


/**
 *
 * @author Melnikov
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    
    private Reader[] readers = new Reader[10];
    private Book[] books = new Book[10];
    private History[] histories = new History[10];
    private User[] users = new User[10];

    private BookManager bookManager = new BookManager(); 
    private ReaderManager readerManager = new ReaderManager(); 
    private UserCardManager userCardManager = new UserCardManager();
    
    private ReadersStorageManager readersStorageManager = new ReadersStorageManager();
    private BooksStorageManager booksStorageManager = new BooksStorageManager();
    private HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
    
    private User loggedInUser;

    public App() {
        Reader[] loadedReaders = readersStorageManager.loadReadersFromFile();
        if(loadedReaders != null){
            readers = loadedReaders;
        }
        Book[] loadedBooks = booksStorageManager.loadBooksFromFile();
        if(loadedBooks != null){
            books = loadedBooks;
        }
        History[] loaderHistories = historiesStorageManager.loadHistoriesFromFile();
        if(loaderHistories != null){
            histories = loaderHistories;
        }
    }

    public void run() {
        System.out.println("--- Библиотека ---");
        SecureManager secureManager = new SecureManager();
        this.loggedInUser = secureManager.checkInLogin(users,readers);
        boolean repeat = true;
        do{
            System.out.println("=============================");
            System.out.println("Задачи:");
            System.out.println("0. Выйти из программы");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Список книг");
            System.out.println("3. Добавить читателя");
            System.out.println("4. Список читателей");
            System.out.println("5. Выдать книгу");
            System.out.println("6. Вернуть книгу");
            System.out.println("7. Список читаемых книг");
            System.out.print("Выберите задачу: ");
            String task = scanner.nextLine();
            System.out.println("=============================");
            switch (task) {
                case "0":
                    System.out.println("--- конец программы ---");
                    repeat = false;
                    break;
                case "1":
                    System.out.println("--- Добавить книгу ---");
                    Book book = bookManager.createBook();
                    bookManager.addBookToArray(book,books);
                    booksStorageManager.saveBooksToFile(books);
                    break;
                case "2":
                    System.out.println("--- Список книг ---");
                    bookManager.printListBooks(books);
                    break;
                case "3":
                    System.out.println("--- Добавить читателя ---");
                    Reader reader = readerManager.createReader();
                    readerManager.addReaderToArray(reader,readers);
                    readersStorageManager.saveReadersToFile(readers);
                    break;
                case "4":
                    System.out.println("--- Список читателей ---");
                    readerManager.printListReaders(readers);
                    break;
                case "5":
                    System.out.println("--- Выдать книгу ---");
                    History history = userCardManager.checkOutBook(books, readers);
                    userCardManager.addHistoryToArray(history,histories);
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "6":
                    System.out.println("--- Вернуть книгу ---");
                    userCardManager.returnBook(histories);
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "7":  
                    System.out.println("--- Список читаемых книг ---");
                    userCardManager.printListReadBooks(histories);
                    break;
                default:
                    System.out.println("Нет такой задачи");;
            }
        }while(repeat);
    }

}
