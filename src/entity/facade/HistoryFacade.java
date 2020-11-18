/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facade;

import entity.History;
import entity.Reader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class HistoryFacade extends AbstractFacade<History>{
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();

    public HistoryFacade(Class<History> entityClass) {
        super(entityClass);
    }
   
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
  
    public List<History> findAll(boolean reading) {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.returnDate = NULL")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<History> findAll(Reader reader) {
        try {
            return em.createQuery("SELECT h FROM History h WHERE h.returnDate = NULL AND h.reader=:reader")
                    .setParameter("reader", reader)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

   

   


}
