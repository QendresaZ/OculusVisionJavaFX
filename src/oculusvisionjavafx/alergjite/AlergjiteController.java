/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.alergjite;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.entities.Alergjite;

/**
 *
 * @author Qendresa
 */
public class AlergjiteController {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public AlergjiteController() {
        emf = Persistence.createEntityManagerFactory("OculusVisionJavaFxPU");
        em = emf.createEntityManager();
    }
    
    public List<Alergjite> getAlergjite() {
        TypedQuery<Alergjite> qry = em.createQuery("select a from Alergjite a", Alergjite.class);
        
        return qry.getResultList();
    }
}
