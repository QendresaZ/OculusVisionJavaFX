/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.klinika;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import oculusvisionjavafx.entities.Alergjite;
import oculusvisionjavafx.entities.Ilac;
import oculusvisionjavafx.entities.Klinika;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.utilis.PersistenceManager;

/**
 *
 * @author Qendresa
 */
public class KlinikaRepository implements KlinikaInterface{

    private EntityManager em = PersistenceManager.getEntityManager();
    
    @Override
    public void add(Klinika k) {
        em.getTransaction().begin();
        em.persist(k);
        em.getTransaction().commit();
    }

    @Override
    public void update(Klinika k) {
        em.getTransaction().begin();
        em.merge(k);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Klinika k) {
        em.getTransaction().begin();
        em.remove(em.merge(k));
        em.getTransaction().commit();
    }

    @Override
    public List<Klinika> getAll() {
        return em.createNamedQuery("Klinika.findAll", Klinika.class).getResultList();
    }

    @Override
    public Klinika findById(int id) {
        return em.find(Klinika.class, id);
    }

    
    public boolean update(Integer id, String emri, String adresa,
            String pershkrimi) {
        em.getTransaction().begin();
        Klinika klinika = findById(id);
        if (klinika != null) {
            klinika.setEmri(emri);
            klinika.setAdresa(adresa);
            klinika.setPershkrimi(pershkrimi);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().commit();
        return false;
    }
    
}
