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
import tools.savers.UsersStorageManager;
import ui.UserInterface;


/**
 *
 * @author Melnikov
 */
public class App {
    private Reader[] readers = new Reader[10];
    private Book[] books = new Book[10];
    private History[] histories = new History[10];
    private User[] users = new User[10];
    private ReadersStorageManager readersStorageManager = new ReadersStorageManager();
    private BooksStorageManager booksStorageManager = new BooksStorageManager();
    private HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
    private UsersStorageManager usersStorageManager = new UsersStorageManager();
    public static User loggedInUser;
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
        User[] loaderUser = usersStorageManager.loadUsersFromFile();
        if(loaderUser != null){
            users = loaderUser;
        }
    }
    public void run() {
        System.out.println("--- Библиотека ---");
        SecureManager secureManager = new SecureManager();
        App.loggedInUser = secureManager.checkInLogin(users,readers);
        UserInterface userInterface = new UserInterface();
        
        if("MANAGER".equals(App.loggedInUser.getRole())){
            //публикуем интерфейс менеджера
            userInterface.printManagerUI(users, readers, books, histories);
        }else if("READER".equals(App.loggedInUser.getRole())){
            //публикуем интерфейс читателя
            userInterface.printReaderUI(users, readers, books, histories);
        }
    }

}
