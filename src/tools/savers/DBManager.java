/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools.savers;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            Object entity = arrayList.get(i);
            em.persist(entity);
        }
        tx.commit();
    }

    @Override
    public List load(String fileName) {
        try {
            String className = null;
            switch (fileName) {
                case "books":
                    className = "Book";
                    break;
                case "readers":
                    className = "Reader";
                    break;
                case "users":
                    className = "User";
                    break;
                case "histories":
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
