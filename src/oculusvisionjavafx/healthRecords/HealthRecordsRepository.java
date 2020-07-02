/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.healthRecords;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import oculusvisionjavafx.entities.Alergjite;
import oculusvisionjavafx.entities.HealthRecords;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.entities.Stafi;
import oculusvisionjavafx.utilis.PersistenceManager;

/**
 *
 * @author Qendresa
 */
public class HealthRecordsRepository implements HealthRecordsInterface {

    private EntityManager em = PersistenceManager.getEntityManager();
    
    @Override
    public void add(HealthRecords h) {
        em.getTransaction().begin();
        em.persist(h);
        em.getTransaction().commit();
    }

    @Override
    public void update(HealthRecords h) {
        em.getTransaction().begin();
        em.merge(h);
        em.getTransaction().commit();
        
    }

    @Override
    public void delete(HealthRecords h) {
        em.getTransaction().begin();
        em.remove(em.merge(h));
        em.getTransaction().commit();
    }

    @Override
    public List<HealthRecords> getAll() {
        return em.createNamedQuery("HealthRecords.findAll", HealthRecords.class).getResultList();
    }

    @Override
    public HealthRecords findById(int id) {
        return em.find(HealthRecords.class, id);
    }

    @Override
    public boolean update(Integer id, Pacienti pacientiID, Stafi stafiID, Date data, String pershkrimi) {
        return false;
    }
    
}
