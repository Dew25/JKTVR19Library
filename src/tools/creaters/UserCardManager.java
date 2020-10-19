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
import java.util.Scanner;
import jktvr19library.App;
import tools.savers.HistoriesStorageManager;

/**
 *
 * @author Melnikov
 */
public class UserCardManager {
    private Scanner scanner = new Scanner(System.in);
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
    private HistoriesStorageManager historiesStorageManager = new HistoriesStorageManager();

    public void checkOutBook(Book[] books, Reader[] readers, History[] histories) {
        System.out.println("--- Список книг ---");
        int bookNumber;
        do{
            if(!bookManager.printListBooks(books)){
                return;
            };
            System.out.print("Выберите номер книги: ");    
            String bookNumberStr = scanner.nextLine();
            try {
                bookNumber = Integer.parseInt(bookNumberStr);
                if(bookNumber < 1 && bookNumber >= books.length){
                    throw new Exception("Выход за диапазон массива книг");
                }
                break;
            } catch (Exception e) {
                System.out.println("Выберите номер из указанного выше списка книг");
            }
        }while(true);
        Book book = books[bookNumber - 1];
        Reader reader = null;
        if("MANAGER".equals(App.loggedInUser.getRole())){
            int readerNumber;
            do{
                System.out.println("--- Список читателей ---");
                readerManager.printListReaders(readers);
                System.out.print("Выберите номер читателя: ");    
                String readerNumberStr = scanner.nextLine();
                try {
                    readerNumber = Integer.parseInt(readerNumberStr);
                    if(readerNumber < 1 && readerNumber >= readers.length){
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного списка");
                }
            }while(true);
            reader = readers[readerNumber - 1];
        }else if("READER".equals(App.loggedInUser.getRole())){
            reader = App.loggedInUser.getReader();
        }
        Calendar calendar = new GregorianCalendar();
        History history = new History(book, reader, calendar.getTime(), null);
        this.addHistoryToArray(history, histories);
       
    }

    public void returnBook(History[] histories) {
        System.out.println("Читаемые книги:");
        if(this.printListReadBooks(histories)){
            int historyNumber;
            do{    
               System.out.println("Выберите номер возвращаемой книги: ");
                String historyNumberStr = scanner.nextLine();
                try {
                    historyNumber = Integer.parseInt(historyNumberStr);
                    if(historyNumber < 1 && historyNumber >= histories.length){
                        throw new Exception("Выход за диапазон массива");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного выше списка");
                }
            }while(true);
            histories[historyNumber - 1].setReturnDate(new GregorianCalendar().getTime());
            historiesStorageManager.saveHistoriesToFile(histories);
        }
    }

    public void addHistoryToArray(History history, History[] histories) {
        for (int i = 0; i < histories.length; i++) {
            if(histories[i] == null){
                histories[i] = history;
                break;
            }
        }
        historiesStorageManager.saveHistoriesToFile(histories);
    }

    public boolean printListReadBooks(History[] histories) {
        boolean notReadBooks = true;
        if("MANAGER".equals(App.loggedInUser.getRole())){
                for (int i = 0;i<histories.length;i++) {
                    if(histories[i] != null && histories[i].getReturnDate() == null){
                        System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                                ,i+1
                                ,histories[i].getBook().getName()
                                ,histories[i].getReader().getFirstname()
                                ,histories[i].getReader().getLastname()
                        );
                        notReadBooks = false;
                    }
                }
                if(notReadBooks){
                    System.out.println("Читаемых книг нет");
                    return false;
                }
            
            
        }else if("READER".equals(App.loggedInUser.getRole())){
                for (int i = 0;i<histories.length;i++) {
                    if(histories[i] != null 
                         && histories[i].getReturnDate() == null
                            && histories[i].getReader().equals(App.loggedInUser.getReader())){
                        System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                                ,i+1
                                ,histories[i].getBook().getName()
                                ,histories[i].getReader().getFirstname()
                                ,histories[i].getReader().getLastname()
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
