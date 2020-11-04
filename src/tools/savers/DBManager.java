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
public class DBManager  implements StorageManagerInterface{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();

    @Override
    public void save(List arrayList, String fileName) {
        tx.begin();
        for (int i = 0; i < arrayList.size(); i++) {
            if(Reader.class.equals(arrayList.get(i).getClass())){
                Reader reader = (Reader)arrayList.get(i);
                if(reader.getId() == null){
                    em.persist(reader);
                }else{
                    em.merge(reader);
                }
                break;
            }
            if(User.class.equals(arrayList.get(i).getClass())){
                User user = (User)arrayList.get(i);
                if(user.getId() == null){
                    em.persist(user);
                }else{
                    em.merge(user);
                }
                break;
            }
            if(Book.class.equals(arrayList.get(i).getClass())){
                Book book = (Book)arrayList.get(i);
                if(book.getId() == null){
                    em.persist(book);
                }else{
                    em.merge(book);
                }
                break;
            }
            if(History.class.equals(arrayList.get(i).getClass())){
                History history = (History)arrayList.get(i);
                if(history.getId() == null){
                    em.persist(history);
                }else{
                    em.merge(history);
                }
                break;
            }
        }
        tx.commit();
    }

    @Override
    public List load(String fileName) {
        try {
            String className = null;
            switch (fileName) {
                case "BOOKS":
                    className = "Book";
                    break;
                case "READERS":
                    className = "Reader";
                    break;
                case "USERS":
                    className = "User";
                    break;
                case "HISTORIES":
                    className = "History";
                    break;
            }
            List arrayList = em.createQuery("SELECT entity FROM "+className+" entity")
                    .getResultList();
            return arrayList;
        } catch (Exception e) {
            System.out.println("База данных пуста");
            return new ArrayList();
        }
        
        
    }
    
}
