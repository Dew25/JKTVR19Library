/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Book;
import entity.History;
import entity.Reader;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import security.SecureManager;
import tools.savers.StorageManager;

/**
 *
 * @author Melnikov
 */
public class UserCardManager {
    private Scanner scanner = new Scanner(System.in);
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private StorageManager storageManager = new StorageManager();

    public void checkOutBook(List<Book> listBooks, List<Reader> listReaders,List<History> listHistories) {
        System.out.println("--- Список книг ---");
        int bookNumber;
        do{
            if(!bookManager.printListBooks(listBooks)){
                return;
            };
            System.out.print("Выберите номер книги: ");    
            String bookNumberStr = scanner.nextLine();
            try {
                bookNumber = Integer.parseInt(bookNumberStr);
                if(bookNumber < 1 && bookNumber >= listBooks.size()){
                    throw new Exception("Выход за диапазон массива книг");
                }
                break;
            } catch (Exception e) {
                System.out.println("Выберите номер из указанного выше списка книг");
            }
        }while(true);
        Book book = listBooks.get(bookNumber - 1);
        Reader reader = null;
        if(SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            int readerNumber;
            do{
                System.out.println("--- Список читателей ---");
                readerManager.printListReaders(listReaders);
                System.out.print("Выберите номер читателя: ");    
                String readerNumberStr = scanner.nextLine();
                try {
                    readerNumber = Integer.parseInt(readerNumberStr);
                    if(readerNumber < 1 && readerNumber >= listReaders.size()){
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного списка");
                }
            }while(true);
            reader = listReaders.get(readerNumber - 1);
        }else if(SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())){
            reader = App.loggedInUser.getReader();
        }
        Calendar calendar = new GregorianCalendar();
        History history = new History(book, reader, calendar.getTime(), null);
        this.addHistoryToArray(history, listHistories);
       
    }

    public void returnBook(List<History> listHistories) {
        System.out.println("Читаемые книги:");
        if(this.printListReadBooks(listHistories)){
            int historyNumber;
            do{    
               System.out.println("Выберите номер возвращаемой книги: ");
                String historyNumberStr = scanner.nextLine();
                try {
                    historyNumber = Integer.parseInt(historyNumberStr);
                    if(historyNumber < 1 && historyNumber >= listHistories.size()){
                        throw new Exception("Выход за диапазон массива");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного выше списка");
                }
            }while(true);
            listHistories.get(historyNumber - 1).setReturnDate(new GregorianCalendar().getTime());
            storageManager.save(listHistories,App.storageFile.HISTORIES.toString());
        }
    }

    public void addHistoryToArray(History history, List<History> listHistories) {
        listHistories.add(history);
        storageManager.save(listHistories,App.storageFile.HISTORIES.toString());
    }

    public boolean printListReadBooks(List<History> listHistories) {
        boolean notReadBooks = true;
        if(SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
                for (int i = 0;i<listHistories.size();i++) {
                    if(listHistories.get(i) != null && listHistories.get(i).getReturnDate() == null){
                        System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                                ,i+1
                                ,listHistories.get(i).getBook().getName()
                                ,listHistories.get(i).getReader().getFirstname()
                                ,listHistories.get(i).getReader().getLastname()
                        );
                        notReadBooks = false;
                    }
                }
                if(notReadBooks){
                    System.out.println("Читаемых книг нет");
                    return false;
                }
            
            
        }else if(SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())){
                for (int i = 0;i<listHistories.size();i++) {
                    if(listHistories.get(i) != null 
                         && listHistories.get(i).getReturnDate() == null
                            && listHistories.get(i).getReader().equals(App.loggedInUser.getReader())){
                        System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                                ,i+1
                                ,listHistories.get(i).getBook().getName()
                                ,listHistories.get(i).getReader().getFirstname()
                                ,listHistories.get(i).getReader().getLastname()
                        );
                        notReadBooks = false;
                    }
                }
                if(notReadBooks){
                    System.out.println("Читаемых книг нет");
                    return false;
                }
            
        }
        return true;
    }
}
