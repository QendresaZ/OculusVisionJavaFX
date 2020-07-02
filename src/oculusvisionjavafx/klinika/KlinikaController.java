/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.klinika;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.entities.Klinika;

/**
 *
 * @author Qendresa
 */
public class KlinikaController {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public KlinikaController() {
        emf = Persistence.createEntityManagerFactory("OculusVisionJavaFxPU");
        em = emf.createEntityManager();
    }
    
    public List<Klinika> getClinicData() {
        TypedQuery<Klinika> qry = em.createQuery("select k from Klinika k ", Klinika.class);
        
        return qry.getResultList();
    }
}
