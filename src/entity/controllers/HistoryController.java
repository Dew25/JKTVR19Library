/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controllers;

import entity.Book;
import entity.History;
import entity.Reader;
import entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class HistoryController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
   
    public void create(History history){
        tx.begin();
        em.persist(history);
        tx.commit();
    }

    public List<History> findAll() {
        try {
            return em.createQuery("SELECT h FROM History h")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
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
    public History find(Long historyId) {
        try {
            return (History) em.createQuery("SELECT h FROM History h WHERE h.id = :id")
                    .setParameter("id", historyId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void edit(History history) {
        tx.begin();
        em.merge(history);
        tx.commit();
    }


}
