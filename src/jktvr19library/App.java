/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19library;

import tools.ReadersStorageManager;
import tools.ReaderManager;
import entity.Reader;
import entity.Book;
import entity.History;
import java.util.GregorianCalendar;
import java.util.Scanner;
import tools.BookManager;
import tools.BooksStorageManager;
import tools.HistoriesStorageManager;
import tools.UserCardManager;


/**
 *
 * @author Melnikov
 */
public class App {
    private Scanner scanner = new Scanner(System.in);
    private Reader[] readers = new Reader[10];
    private Book[] books = new Book[10];
    private History[] histories = new History[10];

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
                    History history = userCardManager.giveBook(books, readers);
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
                    
                    System.out.println("Читаемые книги:");
                    boolean notReadBooks = true;
                    for (int i = 0;i<histories.length;i++) {
                        if(histories[i] != null && histories[i].getReturnDate() == null){
                            System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                                    ,i+1
                                    ,histories[i].getBook().getName()
                                    ,histories[i].getReader().getFirstname()
                                    ,histories[i].getReader().getLastname()
                            );
//                            System.out.println(n+1+". Книгу \""
//                                    +h.getBook().getName()
//                                    +"\" читает "
//                                    +h.getReader().getFirstname() 
//                                    + " "
//                                    +h.getReader().getLastname()
//                            );
                            notReadBooks = false;
                        }
                    }
                    if(notReadBooks){
                        System.out.println("Читаемых книг нет");
                        break;
                    }
                    System.out.println("Выберите номер возвращаемой книги: ");
                    int historyNumber = scanner.nextInt();
                    histories[historyNumber - 1].setReturnDate(new GregorianCalendar().getTime());
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
