/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facade;

import entity.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class BookFacade extends AbstractFacade<Book>{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();

    public BookFacade(Class<Book> entityClass) {
        super(entityClass);
    }
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
