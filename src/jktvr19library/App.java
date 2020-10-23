/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19library;

import tools.savers.ReadersStorageManager;
import entity.Reader;
import entity.Book;
import entity.History;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import security.SecureManager;
import tools.savers.BooksStorageManager;
import tools.savers.HistoriesStorageManager;
import tools.savers.UsersStorageManager;
import ui.UserInterface;


/**
 *
 * @author Melnikov
 */
public class App {

    private List<Reader> listReaders = new ArrayList<>();
    private List<Book> listBooks = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    
    private ReadersStorageManager readersStorageManager = new ReadersStorageManager();
    private BooksStorageManager booksStorageManager = new BooksStorageManager();
    private HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();
    private UsersStorageManager usersStorageManager = new UsersStorageManager();
    public static User loggedInUser;
    public App() {
        List<Reader> loadedReaders = readersStorageManager.loadReadersFromFile();
        if(loadedReaders != null){
            listReaders = loadedReaders;
        }
        List<Book> loadedBooks = booksStorageManager.loadBooksFromFile();
        if(loadedBooks != null){
            listBooks = loadedBooks;
        }
        List<History> loaderHistories = historiesStorageManager.loadHistoriesFromFile();
        if(loaderHistories != null){
            listHistories = loaderHistories;
        }
        List<User> loaderUser = usersStorageManager.loadUsersFromFile();
        if(loaderUser != null){
            listUsers = loaderUser;
        }
    }
    public void run() {
        System.out.println("--- Библиотека ---");
        SecureManager secureManager = new SecureManager();
        App.loggedInUser = secureManager.checkInLogin(listUsers,listReaders);
        UserInterface userInterface = new UserInterface();
        
        if("MANAGER".equals(App.loggedInUser.getRole())){
            //публикуем интерфейс менеджера
            userInterface.printManagerUI(listUsers, listReaders, listBooks, listHistories);
        }else if("READER".equals(App.loggedInUser.getRole())){
            //публикуем интерфейс читателя
            userInterface.printReaderUI(listUsers, listReaders, listBooks, listHistories);
        }
    }

}
