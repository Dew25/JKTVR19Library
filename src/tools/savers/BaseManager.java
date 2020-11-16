/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.savers;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jktvr19library.App;


/**
 *
 * @author Melnikov
 */
public class BaseManager implements StorageManagerInterface{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void save(List arrayList, String fileName) {
        tx.begin();
          for (int i = 0; i < arrayList.size(); i++) {
            if(Book.class.equals(arrayList.get(i).getClass())){
               List<Book> listBooks = (List<Book>) arrayList;
               if(listBooks.get(i).getId() == null){
                   em.persist(listBooks.get(i));
               }
            }
            if(Reader.class.equals(arrayList.get(i).getClass())){
               List<Reader> listReaders = (List<Reader>) arrayList;
               Reader reader = listReaders.get(i);
               if(reader.getId() == null){
                   em.persist(reader);
               }
            }
            if(User.class.equals(arrayList.get(i).getClass())){
               List<User> listUsers = (List<User>) arrayList;
               User user = listUsers.get(i);
               if(user.getId() == null){
                   em.persist(user);
               }
            }
            if(History.class.equals(arrayList.get(i).getClass())){
               List<History> listHistories = (List<History>) arrayList;
               if(listHistories.get(i).getId() == null){
                   em.persist(listHistories.get(i));
               }else{
                   em.merge(listHistories.get(i));
               }
            }
          }
        tx.commit();
    }
    public void save(List arrayList){
        this.save(arrayList,null);
    }
    @Override
    public List load(String fileName) {
        if(App.storageFile.BOOKS.toString().equals(fileName)){
            List<Book> listBooks = em.createQuery("SELECT b FROM Book b")
                    .getResultList();
            return listBooks;
        }
        if(App.storageFile.READERS.toString().equals(fileName)){
            List<Reader> listReaders = em.createQuery("SELECT r FROM Reader r")
                    .getResultList();
            return listReaders;
        }
        if(App.storageFile.USERS.toString().equals(fileName)){
            List<User> listUsers = em.createQuery("SELECT u FROM User u")
                    .getResultList();
            return listUsers;
        }
        if(App.storageFile.HISTORIES.toString().equals(fileName)){
            List<History> listHistories = em.createQuery("SELECT h FROM History h")
                    .getResultList();
            return listHistories;
        }
        return new ArrayList();
    }
    
}
