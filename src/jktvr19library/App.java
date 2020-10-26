/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19library;

import entity.Reader;
import entity.Book;
import entity.History;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import security.SecureManager;

import tools.savers.StorageManager;

import ui.UserInterface;


/**
 *
 * @author Melnikov
 */
public class App {
    public static enum storageFile{BOOKS, READERS, USERS, HISTORIES};
    private List<Reader> listReaders = new ArrayList<>();
    private List<Book> listBooks = new ArrayList<>();
    private List<History> listHistories = new ArrayList<>();
    private List<User> listUsers = new ArrayList<>();
    
    private StorageManager storageManager = new StorageManager();
    
    public static User loggedInUser;
    
    public App() {
        List<Reader> loadedReaders = storageManager.load(App.storageFile.READERS.toString());
        if(loadedReaders != null){
            listReaders = loadedReaders;
        }
        List<Book> loadedBooks = storageManager.load(App.storageFile.BOOKS.toString());
        if(loadedBooks != null){
            listBooks = loadedBooks;
        }
        List<History> loaderHistories = storageManager.load(App.storageFile.HISTORIES.toString());
        if(loaderHistories != null){
            listHistories = loaderHistories;
        }
        List<User> loaderUser = storageManager.load(App.storageFile.USERS.toString());
        if(loaderUser != null){
            listUsers = loaderUser;
        }
    }
    public void run() {
        System.out.println("--- Библиотека ---");
        SecureManager secureManager = new SecureManager();
        App.loggedInUser = secureManager.checkInLogin(listUsers,listReaders);
        UserInterface userInterface = new UserInterface();
        
        if(SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            //публикуем интерфейс менеджера
            userInterface.printManagerUI(listUsers, listReaders, listBooks, listHistories);
        }else if(SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())){
            //публикуем интерфейс читателя
            userInterface.printReaderUI(listUsers, listReaders, listBooks, listHistories);
        }
    }

}
