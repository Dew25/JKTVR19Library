/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.savers;

import entity.Book;
import entity.History;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *
 * @author Melnikov
 */
public class BaseManager implements StrorageManagerInterface{

    @Override
    public void save(List arrayList, String fileName) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
          for (int i = 0; i < arrayList.size(); i++) {
            if(Book.class.equals(arrayList.get(i))){
               List<Book> listBook = (List<Book>) arrayList;
               if(listBook.get(i).getId() == null){
                   em.persist(listBook.get(i));
               }
            }
          }
        tx.commit();
    }
    @Override
    public List load(String fileName) {
        return null;
    }
    
}
