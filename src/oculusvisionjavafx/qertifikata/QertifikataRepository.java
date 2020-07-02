/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.qertifikata;

import com.jfoenix.controls.JFXComboBox;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javax.persistence.EntityManager;
import oculusvisionjavafx.entities.Alergjite;
import oculusvisionjavafx.entities.Ilac;
import oculusvisionjavafx.entities.Llojiqertifikates;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.entities.Qertifikata;
import oculusvisionjavafx.utilis.PersistenceManager;

/**
 *
 * @author Qendresa
 */
public class QertifikataRepository implements QertifikataInterface{

    private EntityManager em = PersistenceManager.getEntityManager();
    
    @Override
    public void add(Qertifikata q) {
        em.getTransaction().begin();
        em.persist(q);
        em.getTransaction().commit();
    }

    @Override
    public void update(Qertifikata q) {
        em.getTransaction().begin();
        em.merge(q);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Qertifikata q) {
        em.getTransaction().begin();
        em.remove(em.merge(q));
        em.getTransaction().commit();
    }

    @Override
    public List<Qertifikata> getAll() {
       return em.createNamedQuery("Qertifikata.findAll", Qertifikata.class).getResultList();
    }

    @Override
    public Qertifikata findById(int id) {
        return em.find(Qertifikata.class, id);
    }
   
      public boolean update(Integer id, Pacienti pacienti, String shenimet, Date dataLeshimit,
                             String semundjet, String simptomat,
                             Llojiqertifikates llojiqertifikatesID){
        em.getTransaction().begin();
        Qertifikata qertifikata = findById(id);
        if (qertifikata != null) {
            qertifikata.setShenimet(shenimet);
            qertifikata.setDataLeshimit(dataLeshimit);
            qertifikata.setSemundjet(semundjet);
            qertifikata.setSimptomat(simptomat);
            qertifikata.setPacientiID(pacienti);
            qertifikata.setLlojiQertifikatesID(llojiqertifikatesID);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().commit();
        return false;
    }
}
