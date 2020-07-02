/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.healthRecords;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.entities.HealthRecords;

/**
 *
 * @author Qendresa
 */
public class HealthRecordsController {

    private EntityManagerFactory emf;
    private EntityManager em;
    
    public HealthRecordsController() {
        emf = Persistence.createEntityManagerFactory("OculusVisionJavaFxPU");
        em = emf.createEntityManager();
    }
    
    public List<HealthRecords> getHealthRecords() {
        TypedQuery<HealthRecords> qry = em.createQuery("select h from HealthRecords h", HealthRecords.class);
        
        return qry.getResultList();
    }
}
