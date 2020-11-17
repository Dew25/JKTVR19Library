/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controllers;

import entity.Reader;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class ReaderController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
   
    public void create(Reader reader){
        tx.begin();
        em.persist(reader);
        tx.commit();
    }

    public List<Reader> findAll() {
        try {
            return em.createQuery("SELECT r FROM Reader r")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Reader find(Long readerId) {
        try {
            return (Reader) em.createQuery("SELECT r FROM Reader r WHERE r.id = :id")
                    .setParameter("id", readerId)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }    
    }
}
