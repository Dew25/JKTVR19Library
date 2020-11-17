/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.creaters;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.controllers.BookController;
import entity.controllers.HistoryController;
import entity.controllers.ReaderController;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import jktvr19library.App;
import security.SecureManager;
import tools.savers.FileManager;
import tools.savers.StorageManagerInterface;

/**
 *
 * @author Melnikov
 */
public class UserCardManager {
    private Scanner scanner = new Scanner(System.in);
    private BookManager bookManager = new BookManager();
    private ReaderManager readerManager = new ReaderManager();
//    private FileManager storageManager = new FileManager();

    public void checkOutBook() {
        System.out.println("--- Список книг ---");
        Long bookNumber;
        do{
            if(!bookManager.printListBooks()){
                return;
            };
            System.out.print("Выберите номер книги: ");    
            String bookNumberStr = scanner.nextLine();
            try {
                bookNumber = Long.parseLong(bookNumberStr);
                break;
            } catch (Exception e) {
                System.out.println("Выберите номер из списка.");
            }
        }while(true);
        BookController bc = new BookController();
        Book book = bc.find(bookNumber);
        Reader reader = null;
        if(SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            Long readerNumber;
            do{
                System.out.println("--- Список читателей ---");
                readerManager.printListReaders();
                System.out.print("Выберите номер читателя: ");    
                String readerNumberStr = scanner.nextLine();
                try {
                    readerNumber = Long.parseLong(readerNumberStr);
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного списка");
                }
            }while(true);
            ReaderController rc = new ReaderController();
            reader = rc.find(readerNumber);
        }else if(SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())){
            reader = App.loggedInUser.getReader();
        }
        Calendar calendar = new GregorianCalendar();
        History history = new History(book, reader, calendar.getTime(), null);
        HistoryController hc = new HistoryController();
        hc.create(history);
    }

    public void returnBook() {
        System.out.println("Читаемые книги:");
        if(this.printListReadBooks()){
            Long historyNumber;
            do{    
               System.out.println("Выберите номер возвращаемой книги: ");
                String historyNumberStr = scanner.nextLine();
                try {
                    historyNumber = Long.parseLong(historyNumberStr);
                    break;
                } catch (Exception e) {
                    System.out.println("Выберите номер из указанного выше списка");
                }
            }while(true);
            HistoryController hc = new HistoryController();
            History history = hc.find(historyNumber);
            history.setReturnDate(new GregorianCalendar().getTime());
            hc.edit(history);
        }
    }

   

    public boolean printListReadBooks() {
        HistoryController hc = new HistoryController();
        if(SecureManager.role.MANAGER.toString().equals(App.loggedInUser.getRole())){
            List<History> listHistories = hc.findAll(true);
            if(listHistories == null){
                System.out.println("Читаемых книг нет");
                return false;
            }
            for (int i = 0;i<listHistories.size();i++) {
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                        ,listHistories.get(i).getId()
                        ,listHistories.get(i).getBook().getName()
                        ,listHistories.get(i).getReader().getFirstname()
                        ,listHistories.get(i).getReader().getLastname()
                );
            }
        }else if(SecureManager.role.READER.toString().equals(App.loggedInUser.getRole())){
            List<History> listHistories = hc.findAll(App.loggedInUser.getReader());
            if(listHistories == null){
                System.out.println("Читаемых книг нет");
                return false;
            }
            for (int i = 0;i<listHistories.size();i++) {
                System.out.printf("%d. Книгу \"%s\" читает %s %s%n"
                        ,listHistories.get(i).getId()
                        ,listHistories.get(i).getBook().getName()
                        ,listHistories.get(i).getReader().getFirstname()
                        ,listHistories.get(i).getReader().getLastname()
                );
            }
        }
        return true;
    }
}
