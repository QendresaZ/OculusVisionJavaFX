 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.utilis;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Qendresa
 */
public class PersistenceManager {
    
    private static EntityManagerFactory emf ;
    
    static {
        emf = Persistence.createEntityManagerFactory("OculusVisionJavaFxPU");
    }
    private PersistenceManager() {
        
    }
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static void close() {
        emf.close();
    }
}
