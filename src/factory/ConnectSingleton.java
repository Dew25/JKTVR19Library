/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Melnikov
 */
public class ConnectSingleton {
    
private EntityManager em;
private EntityManagerFactory emf;
    private static ConnectSingleton instance;
    private ConnectSingleton() {
         emf = Persistence.createEntityManagerFactory("JKTVR19LibraryPU");
         em = emf.createEntityManager();
    }
    public EntityManager getEntityManager(){
        return em;
    }
    public EntityManagerFactory getEntityManagerFactory(){
        return emf;
    }
    public static ConnectSingleton getInstance(){
        if(instance == null){
            instance = new ConnectSingleton();
        }
        return instance;
    }
    
}
