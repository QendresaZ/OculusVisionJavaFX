/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.alergjite;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import oculusvisionjavafx.entities.Alergjite;
import oculusvisionjavafx.entities.Ilac;
import oculusvisionjavafx.entities.Klinika;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.utilis.PersistenceManager;

/**
 *
 * @author Qendresa
 */
public class AlergjiteRepository implements AlergjiteInterface {
    
    private EntityManager em = PersistenceManager.getEntityManager();
    

    @Override
    public void add(Alergjite a) {
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
    }

    @Override
    public void update(Alergjite a) {
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Alergjite a) {
        em.getTransaction().begin();
        em.remove(em.merge(a));
        em.getTransaction().commit();
    }
    
    

    @Override
    public List<Alergjite> getAll() {
        return em.createNamedQuery("Alergjite.findAll", Alergjite.class).getResultList();
    }

    @Override
    public Alergjite findById(int id) {
        return em.find(Alergjite.class, id);
    }

    @Override
    public boolean update(Integer id, int rrezikshmeria, Date dataNodhjes,
            String verejtuarNga, String pershkrimiShtes, Pacienti pacientiID, Ilac ilacID) {
        em.getTransaction().begin();
        Alergjite alergjia = findById(id);
        if (alergjia != null) {
            alergjia.setRrezikshmeria(rrezikshmeria);
            alergjia.setDataNodhjes(dataNodhjes);
            alergjia.setPershkrimiShtes(pershkrimiShtes);
            alergjia.setVerejtuarNga(verejtuarNga);
            alergjia.setPacientiID(pacientiID);
            alergjia.setIlacID(ilacID);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().commit();
        return false;
    }
    
    
}
