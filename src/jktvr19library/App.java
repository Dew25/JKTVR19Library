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
import java.util.GregorianCalendar;
import java.util.Scanner;
import tools.creaters.BookManager;
import tools.savers.BooksStorageManager;
import tools.savers.HistoriesStorageManager;
import tools.creaters.UserCardManager;
import tools.login.LoginManager;


/**
 *
 * @author Melnikov
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private Reader[] readers = new Reader[10];
    private Book[] books = new Book[10];
    private History[] histories = new History[10];
    Reader user = new Reader();

    public App() {
        ReadersStorageManager rsm = new ReadersStorageManager();
        Reader[] loadedReaders = rsm.loadReadersFromFile();
        if(loadedReaders != null){
            readers = loadedReaders;
        }
        BooksStorageManager bsm = new BooksStorageManager();
        Book[] loadedBooks = bsm.loadBooksFromFile();
        if(loadedBooks != null){
            books = loadedBooks;
        }
        HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
        History[] loaderHistories = historiesStorageManager.loadHistoriesFromFile();
        if(loaderHistories != null){
            histories = loaderHistories;
        }
    }

    public void run() {
        
        System.out.println("--- Библиотека ---");
        LoginManager loginManager = new LoginManager();
        Reader logUser = loginManager.login(readers);
        if(logUser == null){
            System.out.println("--- Добавить читателя ---");
            ReaderManager readerManager = new ReaderManager(); 
            Reader reader = readerManager.addReader();
            for (int i = 0; i < readers.length; i++) {
                if(readers[i] == null){
                    readers[i] = reader;
                    break;
                }
            }
            ReadersStorageManager readersStorageManager = new ReadersStorageManager();
            readersStorageManager.saveReadersToFile(readers);
            System.out.println("---------------------");
            do{
                logUser = loginManager.login(readers);
            }while(logUser== null);
        }
        this.user = logUser;
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
                    BookManager bookManager = new BookManager(); 
                    Book book = bookManager.addBook();
                    for (int i = 0; i < books.length; i++) {
                        if(books[i] == null){
                            books[i] = book;
                            break;
                        }
                    }
                    BooksStorageManager booksStorageManager = new BooksStorageManager();
                    booksStorageManager.saveBooksToFile(books);
                    break;
                case "2":
                    System.out.println("--- Список книг ---");
                    int j = 0;
                    for (Book b : books) {
                        if(b != null){
                            System.out.println(j+1+". "+b.toString());
                            j++;
                        }
                    }
                    break;
                case "3":
                    System.out.println("--- Добавить читателя ---");
                    ReaderManager readerManager = new ReaderManager(); 
                    Reader reader = readerManager.addReader();
                    for (int i = 0; i < readers.length; i++) {
                        if(readers[i] == null){
                            readers[i] = reader;
                            break;
                        }
                    }
                    ReadersStorageManager readersStorageManager = new ReadersStorageManager();
                    readersStorageManager.saveReadersToFile(readers);
                    break;
                case "4":
                    System.out.println("--- Список читателей ---");
                    int n = 0;
                    for (Reader r : readers) {
                        if(r != null){
                            System.out.println(n+1+". "+r.toString());
                            n++;
                        }
                    }
                    break;
                case "5":
                    System.out.println("--- Выдать книгу ---");
                    UserCardManager userCardManager = new UserCardManager();
                    History history = userCardManager.giveBook(books, this.user);
                    for (int i = 0; i < histories.length; i++) {
                        if(histories[i] == null){
                            histories[i] = history;
                            break;
                        }
                    }
                    HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "6":
                    System.out.println("--- Вернуть книгу ---");
                    userCardManager = new UserCardManager();
                    userCardManager.returnBook(histories);
                    historiesStorageManager = new HistoriesStorageManager();
                    historiesStorageManager.saveHistoriesToFile(histories);
                    break;
                case "7":  
                    System.out.println("--- Список читаемых книг ---");
                    n = 0;
                    for (History h : histories) {
                        if(h != null && h.getReturnDate() == null){
                            System.out.println(n+1+". "+h.toString());
                            n++;
                        }
                    }
                    break;
                default:
                    System.out.println("Нет такой задачи");;
            }
        }while(repeat);
    }

}
