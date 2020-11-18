/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.facade;

import entity.Reader;
import factory.ConnectSingleton;
import javax.persistence.EntityManager;

/**
 *
 * @author Melnikov
 */
public class ReaderFacade extends AbstractFacade<Reader>{
   
    public ReaderFacade() {
        super(Reader.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        ConnectSingleton connect = ConnectSingleton.getInstance();
        return connect.getEntityManager();
    }
    
}
