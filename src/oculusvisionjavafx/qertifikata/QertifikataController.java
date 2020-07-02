/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.qertifikata;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.entities.Alergjite;
import oculusvisionjavafx.entities.Qertifikata;

/**
 *
 * @author Qendresa
 */
class QertifikataController {
    
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public QertifikataController() {
        emf = Persistence.createEntityManagerFactory("OculusVisionJavaFxPU");
        em = emf.createEntityManager();
    }
    
    public List<Qertifikata> getQertifikatat() {
        TypedQuery<Qertifikata> qry = em.createQuery("select q from Qertifikata q", Qertifikata.class);
        
        return qry.getResultList();
    }
}
