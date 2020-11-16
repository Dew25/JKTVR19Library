/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.controllers;

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
public class UserController {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
   
    public void create(User user){
        tx.begin();
        em.persist(user);
        tx.commit();
    }

    public List<User> findAll() {
        try {
            return em.createQuery("SELECT u FROM User u")
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
